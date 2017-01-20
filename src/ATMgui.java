/*
 * Name : Safik Momin
 * PSID : 1509861
 */


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ATMgui {

	private JFrame frame;
	private JTextField userName;
	private JTextField password;
	private JTextField txtDeposit;
	private JTextField txtwithdraw;
	private JTextField txtTransferAmt;
	private JTextField txtNewPassword;
	private JTextField txtConfirmPassword;
	private int balance;
	private String id;
	private String pass;
	private String fName;
	private String lName;
	private String status;
	private JTextField accNum;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ATMgui window = new ATMgui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void writePFile(){
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			String content = id + " " + pass;
			fw = new FileWriter("Password.txt");
			bw = new BufferedWriter(fw);
			bw.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
	public void writeInfoFile(){
		BufferedWriter bw = null;
		FileWriter fw = null;
		PrintWriter out = null;
		try {
			//String content = id + "\n" + fName+ "\n" + lName + "\n"+ balance + "\n" + status;
			fw = new FileWriter("AccountInformation.txt");
			bw = new BufferedWriter(fw);
			out = new PrintWriter(bw);
			out.println(id);
			out.println(fName);
			out.println(lName);
			out.println(balance);
			out.println(status);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Create the application.
	 */
	public ATMgui() {
		initialize();
	} 

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		String [] accountInfo = new String[5];
		BufferedReader br = null;
		int i = 0;
		try {
			String accountLine;
			br = new BufferedReader(new FileReader("AccountInformation.txt"));
			while ((accountLine = br.readLine()) != null) {
				accountInfo[i] = accountLine;
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		balance = Integer.parseInt(accountInfo[3]);
		fName = accountInfo[1].toString();
		lName = accountInfo[2].toString();
		status = accountInfo[4].toString();
		
		String passwordLine = null;
		try {
			br = new BufferedReader(new FileReader("Password.txt"));
			passwordLine = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		String[] idPass = passwordLine.split(" ");
		id = idPass[0].toString();
		pass = idPass[1].toString();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 825, 408);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(false);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		//creating all panel
		final JPanel login = new JPanel();
		frame.getContentPane().add(login, "name_147108073219277");
		login.setLayout(null);
		
		final JPanel mainMenu = new JPanel();
		frame.getContentPane().add(mainMenu, "name_147108106990804");
		mainMenu.setLayout(null);
		
		//deposit Tab
		final JPanel deposit = new JPanel();
		frame.getContentPane().add(deposit, "name_158034622819604");
		deposit.setLayout(null);
		
		JLabel lblDepositHere = new JLabel("Deposit Here");
		lblDepositHere.setBounds(343, 38, 153, 33);
		deposit.add(lblDepositHere);
		
		txtDeposit = new JTextField();
		txtDeposit.setBounds(263, 86, 236, 39);
		deposit.add(txtDeposit);
		txtDeposit.setColumns(10);
		
		JLabel DepositBalance = new JLabel("New label");
		DepositBalance.setBounds(357, 141, 122, 52);
		deposit.add(DepositBalance);
		
		JButton btnAddDeposit = new JButton("Deposit");
		btnAddDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Amount = txtDeposit.getText();
				int depAmt = Integer.parseInt(Amount);
				balance = depAmt + balance;
				String finalTotal = Integer.toString(balance);
				DepositBalance.setText("$" + finalTotal);
				writeInfoFile();
			}
		});
		btnAddDeposit.setBounds(297, 221, 171, 41);
		deposit.add(btnAddDeposit);
		
		
		
		final JPanel withdraw = new JPanel();
		frame.getContentPane().add(withdraw, "name_158534126126688");
		withdraw.setLayout(null);
		
		JLabel lblWithdrawHere = new JLabel("Withdraw Here");
		lblWithdrawHere.setBounds(337, 28, 166, 33);
		withdraw.add(lblWithdrawHere);
		
		txtwithdraw = new JTextField();
		txtwithdraw.setBounds(267, 109, 236, 39);
		withdraw.add(txtwithdraw);
		txtwithdraw.setColumns(10);
		
		JLabel lblWithAmount = new JLabel("New label");
		lblWithAmount.setBounds(357, 164, 166, 66);
		withdraw.add(lblWithAmount);
		
		JButton btnAddWithdraw = new JButton("Withdraw");
		btnAddWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Amount = txtwithdraw.getText();
				int witAmt = Integer.parseInt(Amount);
				if (witAmt > balance)
				{
					lblWithAmount.setText("Withdrawal Amount exceded");
				}
				else{
					balance = balance - witAmt;
					String finalTotal = Integer.toString(balance);
					lblWithAmount.setText("$" + finalTotal);
					writeInfoFile();
				}
			}
		});
		btnAddWithdraw.setBounds(301, 234, 171, 41);
		withdraw.add(btnAddWithdraw);
		
		final JPanel checkBalance = new JPanel();
		frame.getContentPane().add(checkBalance, "name_161258739409339");
		checkBalance.setLayout(null);
		
		JLabel lblBalance = new JLabel("Your current balance is : ");
		lblBalance.setBounds(83, 140, 180, 33);
		checkBalance.add(lblBalance);
		
		JLabel lblRealBalance = new JLabel("New label");
		lblRealBalance.setBounds(397, 140, 115, 33);
		checkBalance.add(lblRealBalance);
		
		
		
		final JPanel transfer = new JPanel();
		frame.getContentPane().add(transfer, "name_161297292282998");
		transfer.setLayout(null);
		
		JLabel lblTransferHere = new JLabel("Transfer Here");
		lblTransferHere.setBounds(323, 13, 190, 33);
		transfer.add(lblTransferHere);
		
		txtTransferAmt = new JTextField();
		txtTransferAmt.setBounds(266, 128, 236, 39);
		transfer.add(txtTransferAmt);
		txtTransferAmt.setColumns(10);
		
		accNum = new JTextField();
		accNum.setBounds(266, 74, 236, 39);
		transfer.add(accNum);
		accNum.setColumns(10);
		
		JLabel lblAmount = new JLabel("Enter Amount");
		lblAmount.setBounds(103, 131, 115, 33);
		transfer.add(lblAmount);
		
		JLabel lblTOutput = new JLabel("");
		lblTOutput.setBounds(266, 182, 236, 33);
		transfer.add(lblTOutput);
		
		JButton btnAddTransfer = new JButton("Transfer");
		btnAddTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String tAmount = txtTransferAmt.getText();
				String acc = accNum.getText();
				int TCAmount = Integer.parseInt(tAmount);
				balance = balance - TCAmount;
				lblTOutput.setText("You trafered $"+ TCAmount + " to " + acc + " account");
				writeInfoFile();
			}
		});
		btnAddTransfer.setBounds(301, 251, 171, 41);
		transfer.add(btnAddTransfer);
		
		final JPanel changePassword = new JPanel();
		frame.getContentPane().add(changePassword, "name_161316278670961");
		changePassword.setLayout(null);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setBounds(85, 73, 182, 33);
		changePassword.add(lblNewPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setBounds(85, 148, 182, 33);
		changePassword.add(lblConfirmPassword);
		
		txtNewPassword = new JTextField();
		txtNewPassword.setBounds(293, 70, 236, 39);
		changePassword.add(txtNewPassword);
		txtNewPassword.setColumns(10);
		
		txtConfirmPassword = new JTextField();
		txtConfirmPassword.setBounds(293, 145, 236, 39);
		changePassword.add(txtConfirmPassword);
		txtConfirmPassword.setColumns(10);
		
		JLabel lblChgPass = new JLabel("");
		lblChgPass.setBounds(321, 13, 115, 33);
		changePassword.add(lblChgPass);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newPass = txtNewPassword.getText();
				pass = txtConfirmPassword.getText();
				if (newPass.equals(pass))
				{
					lblChgPass.setText("Successful Change");
					writePFile();
				}
				else
				{
					txtNewPassword.setText("Password didn't Match");
					txtConfirmPassword.setText("Password didn't Match");
				}
				
			}
		});
		btnSubmit.setBounds(303, 220, 171, 41);
		changePassword.add(btnSubmit);
		
		//All main menu buttons
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login.setVisible(false);
				mainMenu.setVisible(false);
				deposit.setVisible(true);
				withdraw.setVisible(false);
				checkBalance.setVisible(false);
				transfer.setVisible(false);
				changePassword.setVisible(false);
				String finalBalance = Integer.toString(balance);
				DepositBalance.setText("$" + finalBalance);
			}
		});
		btnDeposit.setBounds(165, 47, 171, 41);
		mainMenu.add(btnDeposit);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(83, 80, 115, 33);
		checkBalance.add(lblName);
		
		JLabel lblOutName = new JLabel("New label");
		lblOutName.setBounds(397, 80, 115, 33);
		checkBalance.add(lblOutName);
		
		JLabel lblStatus = new JLabel("Status :");
		lblStatus.setBounds(83, 201, 115, 33);
		checkBalance.add(lblStatus);
		
		JLabel lblOutStatus = new JLabel("New label");
		lblOutStatus.setBounds(397, 201, 115, 33);
		checkBalance.add(lblOutStatus);
		
		
		JButton btnCheckBalance = new JButton("Check Balance");
		btnCheckBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login.setVisible(false);
				mainMenu.setVisible(false);
				deposit.setVisible(false);
				withdraw.setVisible(false);
				checkBalance.setVisible(true);
				transfer.setVisible(false);
				changePassword.setVisible(false);
				String finalTotal = Integer.toString(balance);
				lblRealBalance.setText("$" + finalTotal);
				lblOutName.setText(fName + " " + lName);
				lblOutStatus.setText(status);
			}
		});
		btnCheckBalance.setBounds(165, 125, 171, 41);
		mainMenu.add(btnCheckBalance);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login.setVisible(false);
				mainMenu.setVisible(false);
				deposit.setVisible(false);
				withdraw.setVisible(false);
				checkBalance.setVisible(false);
				transfer.setVisible(false);
				changePassword.setVisible(true);
				
			}
		});
		btnChangePassword.setBounds(165, 208, 171, 41);
		mainMenu.add(btnChangePassword);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login.setVisible(false);
				mainMenu.setVisible(false);
				deposit.setVisible(false);
				withdraw.setVisible(true);
				checkBalance.setVisible(false);
				transfer.setVisible(false);
				changePassword.setVisible(false);
				String finalTotal = Integer.toString(balance);
				lblWithAmount.setText("$" + finalTotal);
			}
		});
		btnWithdraw.setBounds(471, 47, 171, 41);
		mainMenu.add(btnWithdraw);
		
		JButton btnTransfer = new JButton("Make A Transfer");
		btnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login.setVisible(false);
				mainMenu.setVisible(false);
				deposit.setVisible(false);
				withdraw.setVisible(false);
				checkBalance.setVisible(false);
				transfer.setVisible(true);
				changePassword.setVisible(false);
			}
		});
		btnTransfer.setBounds(471, 125, 171, 41);
		mainMenu.add(btnTransfer);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login.setVisible(true);
				mainMenu.setVisible(false);
				deposit.setVisible(false);
				withdraw.setVisible(false);
				checkBalance.setVisible(false);
				transfer.setVisible(false);
				changePassword.setVisible(false);
			}
		});
		btnLogout.setBounds(471, 208, 171, 41);
		mainMenu.add(btnLogout);
		
		//login
		userName = new JTextField();
		userName.setBounds(322, 76, 236, 39);
		login.add(userName);
		userName.setColumns(10);
		
		
		password = new JTextField();
		password.setBounds(322, 143, 236, 39);
		login.add(password);
		
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(158, 79, 120, 33);
		login.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(165, 146, 113, 33);
		login.add(lblPassword);
		
		JButton btnLogin = new JButton("LogIn");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String UserName = userName.getText();
				String UName = UserName.toString();
				String PassWord = password.getText();
				String PWord = PassWord.toString();
				if (UName.equals(id) && PWord.equals(pass))
				{
				mainMenu.setVisible(true);
				login.setVisible(false);
				deposit.setVisible(false);
				withdraw.setVisible(false);
				checkBalance.setVisible(false);
				transfer.setVisible(false);
				changePassword.setVisible(false);
				}
				else {
					userName.setText("Incorrect Username / Password");
				}
			}
		});
		btnLogin.setBounds(322, 229, 236, 41);
		login.add(btnLogin);
		
		
		JButton btnDBack = new JButton("Go Back");
		btnDBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login.setVisible(false);
				mainMenu.setVisible(true);
				deposit.setVisible(false);
				withdraw.setVisible(false);
				checkBalance.setVisible(false);
				transfer.setVisible(false);
				changePassword.setVisible(false);
			}
		});
		btnDBack.setBounds(12, 13, 87, 33);
		deposit.add(btnDBack);
		
		JButton btnWBack = new JButton("Go Back");
		btnWBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login.setVisible(false);
				mainMenu.setVisible(true);
				deposit.setVisible(false);
				withdraw.setVisible(false);
				checkBalance.setVisible(false);
				transfer.setVisible(false);
				changePassword.setVisible(false);
			}
		});
		btnWBack.setBounds(12, 13, 87, 33);
		withdraw.add(btnWBack);
		
		JButton btnCBBack = new JButton("Go Back");
		btnCBBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login.setVisible(false);
				mainMenu.setVisible(true);
				deposit.setVisible(false);
				withdraw.setVisible(false);
				checkBalance.setVisible(false);
				transfer.setVisible(false);
				changePassword.setVisible(false);
			}
		});
		btnCBBack.setBounds(12, 13, 87, 33);
		checkBalance.add(btnCBBack);
		
		
		
		JButton btnTBack = new JButton("Go Back");
		btnTBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login.setVisible(false);
				mainMenu.setVisible(true);
				deposit.setVisible(false);
				withdraw.setVisible(false);
				checkBalance.setVisible(false);
				transfer.setVisible(false);
				changePassword.setVisible(false);
			}
		});
		btnTBack.setBounds(12, 13, 87, 33);
		transfer.add(btnTBack);
		
		JLabel lblAccountNumber = new JLabel("Account Number");
		lblAccountNumber.setBounds(93, 77, 143, 33);
		transfer.add(lblAccountNumber);
		
		
		
		JButton btnCPBack = new JButton("Go Back");
		btnCPBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login.setVisible(false);
				mainMenu.setVisible(true);
				deposit.setVisible(false);
				withdraw.setVisible(false);
				checkBalance.setVisible(false);
				transfer.setVisible(false);
				changePassword.setVisible(false);
			}
		});
		btnCPBack.setBounds(12, 13, 87, 33);
		changePassword.add(btnCPBack);
		
		
		
	}
}


