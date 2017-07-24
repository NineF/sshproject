package cn.swust.socket;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.swust.domain.Location;
import cn.swust.socket.jdbc.DBoperate;
import cn.swust.util.StringUtil;

public class SocketClient {
	public static ArrayList<String> array=new ArrayList<>();

	public final static int TABLE_COL = 2;

	private JFrame frame;
	private JTable table;
	private JButton button;
	private JTextField text;

	public static void main(String[] args) throws IOException {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SocketClient window = new SocketClient();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SocketClient() {
		initialize();
	}

	private void initialize() {

		for (Location location : DBoperate.getAll()) {
			SocketClient.array.add(location.getLocationname());
		}

		this.frame = new JFrame();
		this.frame.setTitle("模拟下位机列表");
		this.frame.setBounds(100, 100, 450, 300);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 414, 242);
		this.frame.getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 394, 150);

		button = new JButton("添加节点");
		button.setBounds(150, 160, 100, 30);
		text = new JTextField();
		text.setBounds(10, 160, 140, 30);

		panel.add(button);
		panel.add(text);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SocketClient.array.add(text.getText());
				Location location = new Location();
				location.setLocationname(text.getText());
				DBoperate.insert(location);
				//添加节点表
				DBoperate.createlocationSql(StringUtil.getPingYin(text.getText()));
				new Thread(new Runnable() {
					public void run() {
						table.updateUI();
					}
				}).start();
			}
		});

		panel.add(scrollPane);

		this.table = new JTable();
		scrollPane.setViewportView(this.table);

		this.table.setModel(new DefaultTableModel() {
			@Override
			public Object getValueAt(int row, int column) {
				if (column == TABLE_COL - 1) {
					return "连接";
				} else {
					return SocketClient.array.get(row);
				}
			}

			@Override
			public int getRowCount() {
				return SocketClient.array.size();
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

		ClientEditor editor = new ClientEditor();
		editor.setTable(table);
		this.table.getColumnModel().getColumn(TABLE_COL - 1).setCellEditor(editor);

		this.table.getColumnModel().getColumn(TABLE_COL - 1).setCellRenderer(new MyButtonRender());

		this.table.setRowSelectionAllowed(false);// 禁止表格的选择功能。不然在点击按钮时表格的整行都会被选中。也可以通过其它方式来实现。

	}
}
