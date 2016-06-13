package br.com.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

 public class TelaLogin extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;	
			  	
	//Configurações de Fontes
	Font fontBotoes = new Font ("Arial", Font.BOLD, 16 );
	Font fontCaixaLogin =   new Font ("Arial", Font.BOLD, 25 );
	Font fontCaixaSenha =   new Font ("Arial", Font.BOLD, 50 );
	
	//Instancia as variaveis para uso posterior;
	Icon img = new ImageIcon(getClass().getResource("/imagens/fundo.jpg"));
	Icon img2 = new ImageIcon(getClass().getResource("/imagens/logo.png"));
	Icon btconfirm = new ImageIcon(getClass().getResource("/imagens/confirm.png"));
	Icon btexit = new ImageIcon(getClass().getResource("/imagens/exit.png"));
	ImageIcon icon = new ImageIcon("/imagens/favicon(1).ico"); 
	
	//Objetos JLabel
	private JLabel   lsenha = new JLabel(" Senha : ");
	private JLabel  lversao = new JLabel();
	private JLabel llicenca = new JLabel();
	private JLabel  fundo = new JLabel (img);
	private JLabel  logo = new JLabel (img2); 
	private JLabel rLogin = new JLabel("Usuário : ");
	
	//Objetos Botões
	private JButton bConfirma = new JButton(btconfirm);
	private JButton bsair = new JButton(btexit);
	
	//Objetos Caixa de Texto JTextField
	JPasswordField cSenha = new JPasswordField();
	 JTextField cLogin = new JTextField();
	 
	//Variáveis
	 static String user1 = "admin";
	 static String user2= "betto";
	 static String user3 = "vitor";
	 String user4 = "viniciuse";
	 String user5= "viniciusp";
	 String user6 = "nathan";
	 static String password1= "01";
	 static String password2= "02";
	 static String password3= "03";
	 String password4= "04";
	 String password5= "05";
	 String password6= "06";
	 static String operadorAtualNome ;
		 
	//Método de validação de Login	que recebe duas Strings atraves das JTextField da Tela de Login
	 public void validar (String n,String s){ 
			
	   	  // Caso algum dos campos da caixa de texto esteja null
	    	 if (n .equals("" )  ||  s.equals("")){	
	    		 		    JOptionPane.showMessageDialog(null, " Digite um Usuário e uma senha! ", "", -1);	
	    		     	    cLogin.grabFocus();
		     } 
	    	 //Testa as informações de login fornecidas com as variáveis desta classe
	    	 else if (n .equals( user1)&&  s .equals( password1) || 
	    			        n .equals( user2 ) && s .equals( password2)|| 
	    			        n .equals( user3) &&  s .equals( password3 )||
	    		            n .equals( user4)&&  s .equals( password4) || 
	    			        n .equals( user5 ) && s .equals( password5)|| 
	    			        n .equals( user6) &&  s .equals( password6 ))
	    	{	
	    	
	    	//Adiciona o nome do usuário à variavel operadoAtual para aparecer na proxima tela
	    		           JanelaPrincipal.operadorAtual  =  n; 	      		 			  		 			    		 			
	    		 			this.dispose();   
	    		 			new  JanelaPrincipal();  
	    	} 
	 
	    	//No caso de Usuário ou Senha Incorretos
		    else{
		               		JOptionPane.showMessageDialog(null, " Senha ou Usuário Incorretos ! ", "", -1);
			           		cSenha.setText(null);
			        		cSenha.grabFocus();			
	           }    	
	    	     
	   }   
	 
   	//Tratamento dos Botões
	public void actionPerformed(ActionEvent e){
		
		  if (e.getSource()== cSenha){		    	
		     validar(cLogin.getText(), String.valueOf(cSenha.getPassword()));
		    }	
		  
		  if (e.getSource()== cLogin){		    	
		    	cSenha.grabFocus();
			
		    }	
	
		//Se o acesso for pelo botão Confirmar;				
		if(e.getSource() == bConfirma) {		
			 this.validar(cLogin.getText(), String.valueOf(cSenha.getPassword()));
			
			}
							
		//Se o acesso for pelo botão Fechar;
		 if(e.getSource() == bsair)  {
			    int botao = JOptionPane.YES_NO_OPTION;
		    	int escolha = JOptionPane.showConfirmDialog(null,"Deseja fechar a aplicação?", "Sair", botao, -1);
			
		    	if(escolha == JOptionPane.YES_OPTION){	
				System.exit(0); 
		    	}		 
	    	}
	}
	
	//Metodo que cria a janela e seus botões;	
	   public TelaLogin(){  
				
		 //Configurações do JFrame principal;		
		super("SISTEMA DE GERENCIAMENTO - LOGIN" ); 
		setLayout(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setIconImage(new ImageIcon(getClass().getResource("/imagens/Untitled-1.png")).getImage());
		setVisible(true);
		
		
		setExtendedState( MAXIMIZED_BOTH ); 
		setSize(1024,768);
		setLocationRelativeTo(null);  
								
		//Label Texto login 
		rLogin.setFont(new Font("Arial", Font.BOLD, 20 ));
	    rLogin.setForeground(Color.WHITE);
		rLogin.setBounds(155,410,200,60);
	    rLogin.setHorizontalAlignment(SwingConstants.CENTER);
		rLogin.setOpaque (false);
		add(rLogin);
				
		//Label Texto Senha
		lsenha = new JLabel("Senha :");
		lsenha.setFont(new Font("Arial", Font.BOLD, 20 ));
		lsenha.setForeground(Color.WHITE);
		lsenha.setBounds(160,500,200,60);
		lsenha.setHorizontalAlignment(SwingConstants.CENTER);
		lsenha.setOpaque (false);
		add(lsenha);
		
		//Label versao do sistema
		lversao = new JLabel("Sistema de gerenciamento de Mercado  - Curso " +
		"de Informática - Etec Prof º Camargo Aranha - Turma 1IA - 2015 - V.0.1");
		lversao.setFont(new Font("Arial", Font.PLAIN, 12 ));
		lversao.setForeground(Color.WHITE);
		lversao.setBounds(180,15,650,20);
		lversao.setHorizontalAlignment(SwingConstants.CENTER);
		lversao.setOpaque (false);
		add(lversao);
		
		//Label licenca do sistema
		llicenca = new JLabel("Licenciado para Mercado do Aranha - 2015");
		llicenca.setFont(new Font("Arial", Font.PLAIN, 12 ));
		llicenca.setForeground(Color.WHITE);
		llicenca.setBounds(400,700,250,20);
		llicenca.setHorizontalAlignment(SwingConstants.CENTER);
		llicenca.setOpaque (false);
		add(llicenca);
		
		//Botão confirma Login,  e suas configurações;
		bConfirma.addActionListener(this);
		bConfirma.setBounds(640,410,150,50);
		bConfirma.setToolTipText("Confirma Login");
		//bconfirma.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		add(bConfirma);
		
		//Botão fechar e suas configurações;
		bsair.addActionListener(this);
		bsair.setBounds(640,510,150,50);
		bsair.setToolTipText("Encerra o Programa");		
		bsair.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		add(bsair);
		
		//Caixa Digita Login
		cLogin.addActionListener(this);
	    cLogin.setFont(fontCaixaLogin);
		cLogin.setForeground(Color.BLACK);
		cLogin.setOpaque(true);
		cLogin.setBackground(Color.WHITE);
		cLogin.setBounds(320,410,300,50);		
		cLogin.setHorizontalAlignment(SwingConstants.CENTER);
		cLogin.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		add(cLogin);
		cLogin.grabFocus();
				
		//Caixa Digita Senha
		cSenha.addActionListener(this);
		cSenha.setFont(fontCaixaSenha );
		cSenha.setForeground(Color.BLACK);
		cSenha.setBackground(Color.WHITE);
		cSenha.setBounds(320,510,300,50);
		cSenha.setOpaque(true);
		cSenha.setHorizontalAlignment(SwingConstants.CENTER);
		cSenha.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		add(cSenha);
						
		//Imagem Logotipo
		logo.setLayout(null);
		logo.setBounds(85,65,1000,300);
		add(logo);
		
		//Plano de Fundo 
		fundo.setLayout(null);
		fundo.setBackground(Color.DARK_GRAY);
		fundo.setOpaque(true);
		fundo.setBounds(1,1,1022,730);
		add(fundo);	
      	}
	   
  public static void main(String[] args) {
	     DbProdutos.criaConexao();	
	 	  new TelaLogin();
}
}
			
		
	

