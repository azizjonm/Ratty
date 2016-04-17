package de.sogomn.rat.gui.server;

import static de.sogomn.rat.util.Constants.LANGUAGE;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import de.sogomn.rat.gui.AbstractGui;

final class ServerListGui extends AbstractGui {
	
	private JList<String> list;
	private JScrollPane scrollPane;
	private JFormattedTextField port;
	private JButton start, stop;
	
	private static final Dimension SIZE = new Dimension(500, 300);
	private static final NumberFormat PORT_NUMBER_FORMAT = NumberFormat.getInstance();
	
	public static final String START = LANGUAGE.getString("server.start");
	public static final String STOP = LANGUAGE.getString("server.stop");
	
	static {
		PORT_NUMBER_FORMAT.setGroupingUsed(false);
	}
	
	private DefaultListModel<String> listModel;
	
	public ServerListGui() {
		list = new JList<String>();
		scrollPane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		port = new JFormattedTextField(PORT_NUMBER_FORMAT);
		start = new JButton(START);
		stop = new JButton(STOP);
		listModel = new DefaultListModel<String>();
		
		start.setActionCommand(START);
		start.addActionListener(this::buttonClicked);
		stop.setActionCommand(STOP);
		stop.addActionListener(this::buttonClicked);
		list.setModel(listModel);
		
		final JPanel contentPane = createPanel();
		
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setContentPane(contentPane);
		frame.setPreferredSize(SIZE);
		frame.pack();
		frame.setLocationRelativeTo(null);
	}
	
	private JPanel createPanel() {
		final JPanel panel = new JPanel();
		final GridBagLayout layout = new GridBagLayout();
		final GridBagConstraints c = new GridBagConstraints();
		
		panel.setLayout(layout);
		
		c.gridwidth = 2;
		c.weightx = c.weighty = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.BOTH;
		panel.add(scrollPane, c);
		
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(port, c);
		
		c.gridy = 2;
		c.gridwidth = 1;
		panel.add(start, c);
		
		c.gridx = 1;
		panel.add(stop, c);
		
		return panel;
	}
	
	private void buttonClicked(final ActionEvent a) {
		final String command = a.getActionCommand();
		
		notifyListeners(controller -> controller.userInput(command, this));
	}
	
	public void addItem(final String item) {
		listModel.addElement(item);
	}
	
	public void removeItem(final String item) {
		listModel.removeElement(item);
	}
	
	public void setPort(final String text) {
		port.setText(text);
	}
	
	public boolean contains(final String item) {
		return listModel.contains(item);
	}
	
	public String getSelectedItem() {
		return list.getSelectedValue();
	}
	
	public String getPort() {
		return port.getText();
	}
	
}
