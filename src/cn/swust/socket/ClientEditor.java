package cn.swust.socket;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import cn.swust.socket.jdbc.DBoperate;
import cn.swust.util.StringUtil;

public class ClientEditor extends DefaultCellEditor {

	private JPanel panel;

	private JButton button;

	private JTable table;

	public ClientEditor() {
		super(new JTextField());

		this.setClickCountToStart(1);

		this.initButton();

		this.initPanel();

		this.panel.add(this.button);
	}

	private void initButton() {
		this.button = new JButton();

		this.button.setBounds(0, 0, 100, 15);

		this.button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientEditor.this.fireEditingCanceled();
				// DBoperate.delete(SocketManager.array.get(table.getSelectedRow()));
				new Thread(new Runnable() {
					public void run() {
						String location = SocketClient.array.get(table.getSelectedRow());
						Socket socket;
						try {
							socket = new Socket(InetAddress.getLocalHost(), SocketConfig.SOCKET_PORT);
							OutputStream os = null;
							PrintWriter pw = null;
							InputStream is = null;
							os = socket.getOutputStream();
							pw = new PrintWriter(os);
							pw.write(StringUtil.cnToUnicode(location)+"$");
							pw.flush();

							is = socket.getInputStream();
							char c;
							while (true) {
								while ((c = (char) is.read()) == '^') {
									Data data = new Data(getRandom(), getRandom(), location);
									pw.write(data.string());
									pw.flush();
								}
							}
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}).start();

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

		this.panel.setLayout(null);
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.button.setText(value == null ? "" : String.valueOf(value));

		return this.panel;
	}

	@Override
	public Object getCellEditorValue() {
		return this.button.getText();
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	
	private String getRandom(){
		Random r=new Random();
		String s=r.nextDouble()*50+"";
		int i=0;
		for(;i<s.length();i++){
			if(s.charAt(i)=='.')
				break;
		}
		return s.substring(0, i+2);
	}
}
