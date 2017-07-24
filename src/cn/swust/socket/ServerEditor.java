package cn.swust.socket;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ServerEditor extends DefaultCellEditor {

	private JPanel panel;

	private JButton button;

	private JTable table;

	public ServerEditor() {
		// DefautlCellEditor有此构造器，需要传入一个，但这个不会使用到，直接new一个即可。
		super(new JTextField());

		// 设置点击几次激活编辑。
		this.setClickCountToStart(1);

		this.initButton();

		this.initPanel();

		// 添加按钮。
		this.panel.add(this.button);
	}

	private void initButton() {
		this.button = new JButton();

		// 设置按钮的大小及位置。
		this.button.setBounds(0, 0, 100, 15);

		// 为按钮添加事件。这里只能添加ActionListner事件，Mouse事件无效。
		this.button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 触发取消编辑的事件，不会调用tableModel的setValue方法。
				ServerEditor.this.fireEditingCanceled();
				int index=table.getSelectedRow();				
				Thread t=SocketManager.threads.get(index);
				t.stop();
				SocketManager.threads.remove(index);
				SocketServer.locations.remove(index);
				
				new Thread(new Runnable() {
					public void run() {
						table.updateUI();
					}
				}).start();
			}
		});

	}

	private void initPanel() {
		this.panel = new JPanel();

		// panel使用绝对定位，这样button就不会充满整个单元格。
		this.panel.setLayout(null);
	}

	/**
	 * 这里重写父类的编辑方法，返回一个JPanel对象即可（也可以直接返回一个Button对象，但是那样会填充满整个单元格）
	 */
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		// 只为按钮赋值即可。也可以作其它操作。
		this.button.setText(value == null ? "" : String.valueOf(value));
		return this.panel;
	}

	/**
	 * 重写编辑单元格时获取的值。如果不重写，这里可能会为按钮设置错误的值。
	 */
	@Override
	public Object getCellEditorValue() {
		return this.button.getText();
	}

	public void setTable(JTable table) {
		this.table = table;
	}

}
