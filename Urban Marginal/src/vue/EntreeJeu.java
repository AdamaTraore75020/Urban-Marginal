package vue;

import controleur.Controle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JTextField;

public class EntreeJeu extends JFrame {

	private JPanel contentPane;
	private Controle controle;
	private JTextField txtIp;
	private String ip;
	/**
	 * Launch the application.
	 */

	private void btnStart_clic() {
		controle.evenementVue(this, "serveur");
		System.out.println(ip);
	}

	private void btnExit_clic() {
		System.exit(0);

	}

	private void btnConnect_clic() {
		controle.evenementVue(this, txtIp.getText());
	}

	/**
	 * Create the frame.
	 * 
	 * @param controle
	 */
	public EntreeJeu(Controle controle) {
		setTitle("Urban Marginal TP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 358, 257);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("D\u00E9marrer un serveur :");
		lblNewLabel.setBounds(22, 29, 178, 28);
		contentPane.add(lblNewLabel);

		JLabel lblConnectToAn = new JLabel("Serveur d\u00E9j\u00E0 existant ? Connecte-toi :");
		lblConnectToAn.setBounds(22, 84, 250, 34);
		contentPane.add(lblConnectToAn);

		JLabel lblNewLabel_1 = new JLabel("IP du serveur :");
		lblNewLabel_1.setBounds(22, 122, 95, 34);
		contentPane.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Start");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnStart_clic();
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(223, 32, 109, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Connection");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnConnect_clic();
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1.setBounds(223, 128, 109, 23);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Quitter");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnExit_clic();
			}
		});
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(223, 184, 109, 23);
		contentPane.add(btnNewButton_2);

		txtIp = new JTextField();
		txtIp.setText("127.0.0.1");
		txtIp.setBounds(127, 129, 86, 20);
		contentPane.add(txtIp);
		txtIp.setColumns(10);
		
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			System.out.println("pas d'adresse IP ? "+ e1);
		}
		this.controle = controle;
	}
}
