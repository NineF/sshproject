package cn.swust.socket;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SocketServer {
	private static ServerSocket server;
	public static ArrayList<String> locations = new ArrayList<>();

	public final static int TABLE_COL = 2;

	public JFrame frame;
	private static JTable table;

	public static void main(String[] args) throws IOException {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SocketServer window = new SocketServer();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		server = new ServerSocket(SocketConfig.SOCKET_PORT);
		while (true) {
			Socket socket = server.accept();
			SocketThread thread = new SocketThread(socket);
			Thread t = new Thread(thread);
			SocketManager.threads.add(t);
			t.start();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			table.updateUI();
		}
	}

	public SocketServer() {
		initialize();
	}

	private void initialize() {

		this.frame = new JFrame();
		this.frame.setTitle("服务器节点列表");
		this.frame.setBounds(100, 100, 450, 300);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 414, 242);
		this.frame.getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 394, 150);

		panel.add(scrollPane);

		this.table = new JTable();
		scrollPane.setViewportView(this.table);

		this.table.setModel(new DefaultTableModel() {
			@Override
			public Object getValueAt(int row, int column) {
				if (column == TABLE_COL - 1) {
					return "断开连接";
				} else {
					return locations.get(row);
				}
			}

			@Override
			public int getRowCount() {
				return locations.size();
			}

			@Override
			public int getColumnCount() {
				return TABLE_COL;
			}

			@Override
			public void setValueAt(Object aValue, int row, int column) {
				System.out.println(aValue + "  setValueAt");
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				// 带有按钮列的功能这里必须要返回true不然按钮点击时不会触发编辑效果，也就不会触发事件。
				if (column == TABLE_COL - 1) {
					return true;
				} else {
					return false;
				}
			}
		});

		ServerEditor editor = new ServerEditor();
		editor.setTable(table);
		this.table.getColumnModel().getColumn(TABLE_COL - 1).setCellEditor(editor);

		this.table.getColumnModel().getColumn(TABLE_COL - 1).setCellRenderer(new MyButtonRender());

		this.table.setRowSelectionAllowed(false);// 禁止表格的选择功能。不然在点击按钮时表格的整行都会被选中。也可以通过其它方式来实现。

	}
}
