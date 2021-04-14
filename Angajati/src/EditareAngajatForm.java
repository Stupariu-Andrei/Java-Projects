import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditareAngajatForm extends JFrame{
	private Angajat angajat;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtAge;
	private JTextField txtGender;
	private JTextField txtRole;
	
	public EditareAngajatForm(Angajat obj) {
		angajat = obj;
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(null);
		JLabel lbl = new JLabel("Id");
		lbl.setBounds(10, 10, 85, 20);
		panel.add(lbl);
		lbl = new JLabel("Name");
		lbl.setBounds(10, 40, 85, 20);
		panel.add(lbl);
		lbl = new JLabel("Age");
		lbl.setBounds(10, 70, 85, 20);
		panel.add(lbl);
		lbl = new JLabel("Gender");
		lbl.setBounds(10, 100, 85, 20);
		panel.add(lbl);
		lbl = new JLabel("Role");
		lbl.setBounds(10, 130, 85, 20);
		panel.add(lbl);
		
		txtId = new JTextField(""+obj.getId());
		txtId.setBounds(110, 10, 150, 20);
		panel.add(txtId);
		
		txtName = new JTextField(obj.getName());
		txtName.setBounds(110, 40, 150, 20);
		panel.add(txtName);

		txtAge = new JTextField(""+obj.getAge());
		txtAge.setBounds(110, 70, 150, 20);
		panel.add(txtAge);

		txtGender = new JTextField(obj.getSex());
		txtGender.setBounds(110, 100, 150, 20);
		panel.add(txtGender);

		txtRole = new JTextField(obj.getRole());
		txtRole.setBounds(110, 130, 150, 20);
		panel.add(txtRole);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//EditareAngajatForm.this.setVisible(false);
				//parent.reloadTable();
				//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
			}
		});
		btnCancel.setBounds(10, 170, 90, 40);
		panel.add(btnCancel);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				angajat.setId(Integer.parseInt(txtId.getText()));
				angajat.setName(txtName.getText());
				angajat.setAge(Integer.parseInt(txtAge.getText()));
				angajat.setSex(txtGender.getText());
				angajat.setRole(txtRole.getText());
				//EditareAngajatForm.this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				//parent.getModel().setValueAt(txtName.getText(), 0, 0);
				//parent.getModel().addTableModelListener();
				//parent.reloadTable();
				EditareAngajatForm.this.dispose();  
			}
		});
		btnSave.setBounds(110, 170, 90, 40);
		panel.add(btnSave);
		
		this.setTitle("Editare Angajat");
		panel.setSize(250, 250);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.pack();
	}
}
