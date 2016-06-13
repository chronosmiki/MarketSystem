package br.com.system;

import static java.awt.event.KeyEvent.VK_F2;
import static java.awt.event.KeyEvent.VK_F3;
import static java.awt.event.KeyEvent.VK_Q;
import static java.awt.event.KeyEvent.VK_W;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class JanelaPrincipal extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	DecimalFormat df = new DecimalFormat("#,###.00");
	
	// Configura��o de Fontes
	Font fontContainer = new Font("Times New Roman", Font.PLAIN,18);
	Font fontDescricao = new Font("Arial", Font.BOLD, 25);
	Font fontEstadoCaixa = new Font("Arial", Font.BOLD, 50);
	Font fontCaixasDeEntrada = new Font("Arial", Font.BOLD, 40);
	Font fontLabelsCaixas = new Font("Arial", Font.BOLD, 20);
	static Font fontOperador = new Font("Arial", Font.BOLD, 18);
	Font fontBotoesSuperiores = new Font("Arial", Font.BOLD, 12);
	Font fontBotoesInferiores = new Font("Arial", Font.BOLD, 25);
	
	static ArrayList <String>lista = new ArrayList<>();
	static ArrayList <Integer>codigo = new ArrayList<>();
	static ArrayList <Double>preco = new ArrayList<>();
	
	// Imagens e Icones
	
	//Fav Icon
	ImageIcon icon = new ImageIcon("/imagens/favicon(1).ico");  
	
	// Papel de Parede
	ImageIcon planoFundo = new ImageIcon(getClass().getResource("/imagens/fundo.jpg"));

	// Imagem para Bot�o excluir da lista
	ImageIcon imgBExcl = new ImageIcon(getClass().getResource("/imagens/excluir.png"));

	// Imagem para Bot�o finalizar compra
	ImageIcon imgBfinalizarCompra = new ImageIcon(getClass().getResource("/imagens/finalizarCompra.png"));

	// Imagem para Bot�o nova venda
	// ImageIcon imgBnovaVenda = new
	// ImageIcon(getClass().getResource("/imagens/novaVenda.png"));

	// Imagem para Bot�o nova venda
	ImageIcon imglLogotipo = new ImageIcon(getClass().getResource("/imagens/logoTelaCaixa.png"));

	// Bot�es
	private JButton bEstoque = new JButton("Estoque");
	private JButton bTrocaUsuario = new JButton("Trocar de Usu�rio");
	private JButton bInfo = new JButton("Sobre");
	private JButton bSair = new JButton("Sair");
	private JButton bExclCarrinho = new JButton(imgBExcl);
	private JButton bFinalizarCompra = new JButton(imgBfinalizarCompra);
	private JButton bFechaCaixa = new JButton("ABRIR CAIXA");

	// Objetos JLabel
	String zero = df.format(0.00);
	String rotuloOperador = "";
	private JLabel dataHora = new JLabel();
	static JLabel lContainer = new JLabel();
	static JLabel lEstadoCaixa = new JLabel();
	private JLabel lTopo = new JLabel();
	private JLabel lFundo = new JLabel(planoFundo);
	private JLabel rCcodigo = new JLabel("C�digo do Produto :");
	private JLabel rCquant = new JLabel("Quantidade :");
	private JLabel rCvalorUnit = new JLabel("Valor Unit�rio R$ :");
	static JLabel cValorUnit = new JLabel();
	private JLabel rCvalorTotal = new JLabel("Total R$ ");
	static JLabel lValorTotal = new JLabel(""
			+ "");
	private JLabel loperador = new JLabel(rotuloOperador + operadorAtual);
	static JLabel lDescricao = new JLabel("");
	private JLabel lLogotipo = new JLabel(imglLogotipo);

	// Objetos JTextField
	static JTextField cCodProd = new JTextField("");
	static JTextField cQuant = new JTextField("");
	static JTextField cValorDesc = new JTextField("");

	// Vari�veis
	//String totalUnitario = String	.valueOf(Double.parseDouble(cValorUnit.getText()) * Integer.parseInt(cQuant.getText()));
	static String operadorAtual = TelaLogin.operadorAtualNome;
	static double totalVenda = 0.0;
	double valorUnit = 0.0;
	String listaProdutos = "";
	static String infoContainer ="";
	static String descricaoProduto;
	//double valorUnitario = Double.parseDouble(cValorUnit.getText());
	static double valorInicialCaixa = 0.0;
	static double lucroDoDia = 0.0;
	static int quantVendasDia = 0;
	static int cod =0;
    
	// Objeto Scroll
	private JScrollPane sContainer = new JScrollPane(lContainer);
    
	// Tratamento das a��es dos bot�es
	public void actionPerformed(ActionEvent e) {
     
		// Caso seja Clicado o Botao finalizar compra
		if (e.getSource() == bFinalizarCompra) {
			if (lValorTotal.getText() == null || lValorTotal.getText() == ("0.00")
					|| lValorTotal.getText() == ("")) {
				JOptionPane.showMessageDialog(null, "Voc� precisa realizar uma venda para finalizar !", "", 0);
				cCodProd.grabFocus();
				lDescricao.setText("\nDescri��o do Produto");
			} else {

				new JanelaFinalizaVenda();
				JanelaFinalizaVenda.lTotalPagar.setText(df.format(totalVenda
						
				));
			}
		}
		
		// Caso seja clicado no bot�o Fechar / Abrir Caixa
		if (e.getSource() == bFechaCaixa) {
			if (lEstadoCaixa.getText().equals("NOVA VENDA")) {
				JOptionPane.showMessageDialog(null, "Voc� precisa finalizar a venda atual para fechar o caixa !", 	"A��o Necess�ria", 2);
				int bt3 = JOptionPane.YES_NO_OPTION;
				int choose3 = JOptionPane.showConfirmDialog(null, "Finalizar Venda ?", "", bt3, -1);

				if (choose3 == JOptionPane.YES_OPTION) {

					new JanelaFinalizaVenda();
					JanelaFinalizaVenda.lTotalPagar.setText(String.valueOf(totalVenda));
				}

				if (choose3 == JOptionPane.NO_OPTION) {

					cCodProd.grabFocus();
				}
				
			}
			else if(lEstadoCaixa.getText().equals("CAIXA LIVRE")) {

				int bt = JOptionPane.YES_NO_OPTION;
				int choose = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja fechar o Caixa ? ",
						"Fechamento de Caixa", bt, -1);

				if (choose == JOptionPane.YES_OPTION) {
					lEstadoCaixa.setText("CAIXA FECHADO");
					lEstadoCaixa.setForeground(Color.RED);
					cCodProd.setEnabled(false);
					cQuant.setEnabled(false);
					bFinalizarCompra.setEnabled(false);
					bFechaCaixa.setText("ABRIR CAIXA");
					bExclCarrinho.setEnabled(false);
					rCvalorUnit.setEnabled(false);
					rCvalorTotal.setEnabled(false);
					rCcodigo.setEnabled(false);
					rCquant.setEnabled(false);
					cCodProd.setText("");
					cQuant.setText("");
					cValorUnit.setEnabled(false);
					lValorTotal.setEnabled(false);
					lContainer.setText("");
					lDescricao.setText("");
					cValorUnit.setText("");
					totalVenda = 0;

					JOptionPane.showMessageDialog(null,
							"Fechamento de Caixa \n\n" + "Operador : " + operadorAtual + "\n\n" +

					"Caixa aberto com o valor de R$ : " + df.format(valorInicialCaixa)
									+ "\nA quantidade de vendas de hoje foram : " + quantVendasDia
									+ "\nO lucro do dia � : R$ " + df.format(lucroDoDia) + "\nO valor total em caixa �  R$ "
									+ df.format((valorInicialCaixa + lucroDoDia)) + "\n\n",
							"Fechamento de Caixa", -1);
					valorInicialCaixa = 0.0;
					quantVendasDia = 0;
				}

				if (choose == JOptionPane.NO_OPTION) {

				}

			} else if (lEstadoCaixa.getText().equals("CAIXA FECHADO")) {
				int bt = JOptionPane.YES_NO_OPTION;
				int choose = JOptionPane.showConfirmDialog(null, "Deseja Abrir o Caixa ? ", "Abertura de Caixa", bt,
						-1);

				if (choose == JOptionPane.YES_OPTION) {
					lEstadoCaixa.setText("CAIXA LIVRE");
					lEstadoCaixa.setForeground(Color.YELLOW);
					cCodProd.setEnabled(true);
					cQuant.setEnabled(true);
					bFinalizarCompra.setEnabled(true);
					bFechaCaixa.setText("FECHAR CAIXA");
					bExclCarrinho.setEnabled(true);
					rCvalorUnit.setEnabled(true);
					rCvalorTotal.setEnabled(true);
					rCquant.setEnabled(true);
					rCcodigo.setEnabled(true);
					cValorUnit.setText("");
					cCodProd.setText("");
					cCodProd.grabFocus();
					cValorUnit.setEnabled(true);
					lValorTotal.setEnabled(true);
					lContainer.setText("Aguardando Pr�xima Venda");
					cValorUnit.setText("");
					lucroDoDia = 0.0;

					valorInicialCaixa = Double.parseDouble(JOptionPane.showInputDialog(null,
							"Digite o valor inicial em caixa", "Abertura de Caixa", -1));
				}

				JOptionPane.showMessageDialog(null,
						"Caixa Aberto por " + operadorAtual + "\n" + "Valor em caixa :  R$ " + df.format(valorInicialCaixa),
						"Abertura de Caixa", -1);

				if (choose == JOptionPane.NO_OPTION) {

				}
			}
		}

		// Caso seja Clicado enter na Caixa c�digo do produto
		if (e.getSource() == cCodProd) {

			// Metodo que adiciona uma a��o ao ccoProd perder o foco, neste caso
			// enquando o banco de dados nao estiver conectado mostra mensagem
			cCodProd.addFocusListener(new FocusAdapter() {
				public void focusLost(FocusEvent e) {
					if (lDescricao.getText().equals("")) {
						JOptionPane.showMessageDialog(null,
								"A conex�o com o Banco de Dados esta sendo estabelecida.\n Tente novamente.");
						cCodProd.grabFocus();

					}
				}
			});

			if (cCodProd.getText().equals("")) {

				JOptionPane.showMessageDialog(null, "Digite o C�digo do Produto!", "Preenchimento Obrigat�rio", 2);
				cCodProd.grabFocus();
				lDescricao.setText("Decri��o do Produto");
				cQuant.setText("");
			} else {
				if (lContainer.getText().equals("<html><br>" + "Aguandando pr�xima venda")) {
					lContainer.setText(null);
				}
				if (!cQuant.getText().equals("0")) {

					if (DbProdutos.buscaDado(Integer.parseInt(cCodProd.getText())).equals("PRODUTO N�O ENCONTRADO")) {
						lDescricao.setForeground(Color.RED);
						lDescricao.setText("Produto n�o cadastrado");
						cCodProd.setText(null);
						cQuant.setText(null);
						cCodProd.grabFocus();
						cValorUnit.setText(null);
					} 
								
					else {
						lDescricao.setForeground(Color.YELLOW);
						descricaoProduto = DbProdutos.buscaNome(Integer.parseInt(cCodProd.getText()));
						valorUnit = DbProdutos.buscaPreco(Integer.parseInt(cCodProd.getText()));
						cValorUnit.setText(df.format(valorUnit));
						cQuant.grabFocus();
						lDescricao.setText(descricaoProduto);
					}
				} else {
					lDescricao.setForeground(Color.YELLOW);
					descricaoProduto = "";
					cQuant.setText("");
					cQuant.grabFocus();
					lDescricao.setText(descricaoProduto);
					if (lContainer.getText().equals("Aguandando pr�xima venda")) {
						lContainer = null;
					}
				}
			}
		}
		  
    	// Caso seja Clicado enter na caixa quantidade
		if (e.getSource() == cQuant) {
          

			if (cCodProd.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Digite o C�digo do Produto !", "Preenchimento Obrigat�rio", 2);
				cCodProd.setText("");
				cCodProd.grabFocus();
			} else if (cQuant.getText().equals("") && lEstadoCaixa.getText().equals("CAIXA LIVRE")) {
				cCodProd.grabFocus();
				cCodProd.setText("");
				cValorUnit.setText("");
				lDescricao.setText("Descri��o do Produto");
			} else if (cQuant.getText().equals("") && lEstadoCaixa.getText().equals("NOVA VENDA")) {
				JOptionPane.showMessageDialog(null, "Digite a Quantidade do Produto!", "Preenchimento Obrigat�rio", 2);
			}

			else {
			   if (Integer.parseInt(cQuant.getText())<1){
				   JOptionPane.showMessageDialog(null, "Digite um n�mero maior que zero !", "Preenchimento Obrigat�rio", 2);
				   cQuant.setText(null);
				   cQuant.grabFocus();
			   }
				   

			    else{
				    
			   	preco.add(Integer.parseInt(cQuant.getText())  *   DbProdutos.buscaPreco(Integer.parseInt(cCodProd.getText())));
			   	 for(int x=0; x<=lista.size();x++){
			    	    cod=x;
			   	 }
			   	codigo.add(cod);
				totalVenda += Integer.parseInt(cQuant.getText()) * valorUnit;				
				lista.add("<html>"  + " - " + "Quant : " + cQuant.getText() + " - " + 
				DbProdutos.buscaNome(Integer.parseInt(cCodProd.getText())) + " - VL Unit  R$ "
				+  cValorUnit.getText() + "<br>");	
				
			    infoContainer +=  "Produto " +	JanelaPrincipal.codigo.get(cod)+ " - " + lista.get(cod );    
			   							
				lContainer.setText("<html><br>" +infoContainer);
				
				lValorTotal.setText(df.format(totalVenda));
				cCodProd.setText(null);
				lEstadoCaixa.setForeground(Color.GREEN);
				lEstadoCaixa.setText("NOVA VENDA");
				cQuant.setText(null);
				lDescricao.setText(null);
				lDescricao.setText("Descri��o do Produto");
				descricaoProduto = null;
				cCodProd.grabFocus();
				cValorUnit.setText(null);
			   }
			}
		}

		// Caso seja Clicado o Botao Troca de Usu�rio
		if (e.getSource() == bTrocaUsuario) {

			if (lEstadoCaixa.getText() == "NOVA VENDA") {
				JOptionPane.showMessageDialog(null, "Voc� precisa finalizar a venda atual para trocar de operador !",
						"A��o Necess�ria", 2);
				int bt3 = JOptionPane.YES_NO_OPTION;
				int choose3 = JOptionPane.showConfirmDialog(null, "Finalizar Venda ?", "", bt3, -1);

				if (choose3 == JOptionPane.YES_OPTION) {

					new JanelaFinalizaVenda();
					JanelaFinalizaVenda.lTotalPagar.setText(String.valueOf(totalVenda));
				}

				if (choose3 == JOptionPane.NO_OPTION) {

					cCodProd.grabFocus();
				}
			} else {
				int bt2 = JOptionPane.YES_NO_OPTION;
				int choose2 = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja trocar o operador ?", "", bt2,
						-1);

				if (choose2 == JOptionPane.YES_OPTION) {
					new TelaLogin();
					this.dispose();
				}
			}
		}
		// Caso seja Clicado o Botao Sobre
		if (e.getSource() == bInfo) {
			JOptionPane.showMessageDialog(null,
					"Sistema de Gerenciamento de com�rcio aliment�cio\n" + "\n"
							+ "Este sistema foi desenvolvido pelos alunos no 1� m�dulo do Curso de Inform�tica da\n"
							+ "Etec Prof � Camargo Aranha como atividade dde final de semestre da mat�ria de l�gica\n"
							+ "de programa��o sob a orienta��o do professor Bruno Cano Neste projeto abordamos a\n"
							+ "programa��o Orientada a Objetos, bem como recursos do pacote gr�fico Swing entre\n"
							+ "outros recursos interessantes, dispon�veis para enriquecer uma aplica��o , mesmo\n"
							+ "que simples como essa.\n\n" + "Grupo: \n\n" + "Vinicius de Jesus Pinto\n"
							+ "Vinicius Emanuel\n" + "Vitor Pereira\n" + "Nathan Susuki\n" + "Roberto Xavier Collura\n",
					"Informa��es do Projeto", -1);
		}

		// Caso seja Clicado o Bot�o Estoque
		if (e.getSource() == bEstoque) {
			new JanelaEstoque();
		}
		// Caso seja Clicado o Botao Sair
		if (e.getSource() == bSair) {
			
			if (lEstadoCaixa.getText() == "NOVA VENDA") {
				JOptionPane.showMessageDialog(null, "Voc� precisa finalizar a venda atual para fechar a aplica��o",
						"A��o Necess�ria", 2);
				int bt3 = JOptionPane.YES_NO_OPTION;
				int choose3 = JOptionPane.showConfirmDialog(null, "Finalizar Venda ?", "", bt3, -1);

				if (choose3 == JOptionPane.YES_OPTION) {

					new JanelaFinalizaVenda();
					JanelaFinalizaVenda.lTotalPagar.setText(String.valueOf(df.format(totalVenda)));
				}

				if (choose3 == JOptionPane.NO_OPTION) {

					cCodProd.grabFocus();
				}
			}
			
			else if (lEstadoCaixa.getText().equals("CAIXA FECHADO")){
				int bt = JOptionPane.YES_NO_OPTION;
				int choose = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja fechar a aplica��o?", "", bt, -1);

				if (choose == JOptionPane.YES_OPTION) {
				      System.exit(0);
				}
			}
			else{

			int bt = JOptionPane.YES_NO_OPTION;
			int choose = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja fechar a aplica��o?", "", bt, -1);

			if (choose == JOptionPane.YES_OPTION) {
				lEstadoCaixa.setText("CAIXA FECHADO");
				lEstadoCaixa.setForeground(Color.RED);
				cCodProd.setEnabled(false);
				cQuant.setEnabled(false);
				bFinalizarCompra.setEnabled(false);
				bFechaCaixa.setText("ABRIR CAIXA");
				bExclCarrinho.setEnabled(false);
				rCvalorUnit.setEnabled(false);
				rCvalorTotal.setEnabled(false);
				rCcodigo.setEnabled(false);
				rCquant.setEnabled(false);
				cCodProd.setText("");
				cQuant.setText("");
				cValorUnit.setEnabled(false);
				lValorTotal.setEnabled(false);
				lContainer.setText("");
				lDescricao.setText("");
				cValorUnit.setText("");
				totalVenda = 0;

				JOptionPane.showMessageDialog(null,
						"Fechamento de Caixa \n\n" + "Operador : " + operadorAtual + "\n\n" +

				"Caixa aberto com o valor de R$ : " + df.format(valorInicialCaixa)
								+ "\nA quantidade de vendas de hoje foram : " + quantVendasDia
								+ "\nO lucro do dia � : R$ " +df.format( lucroDoDia) + "\nO valor total em caixa �  R$ "
								+ df.format((valorInicialCaixa + lucroDoDia)) + "\n\n",
						"Fechamento de Caixa", -1);
				valorInicialCaixa = 0.0;
				quantVendasDia = 0;
				System.exit(0);
			}
		}
	}
	
		// Caso seja Clicado o Botao Excluir produto da lista
		if (e.getSource() == bExclCarrinho) {
			new LoginExcluiProdutoDaLIsta();
		} 
	}
			 	
	// Libera Bot�o Estoque somente se o usurario for admin
	public void liberaBotaoEstoque(String op) {
		if (op.equals("admin") || op.equals("betto") || op.equals("vitor")) {
			rotuloOperador += "Administrador : ";
			loperador.setText(rotuloOperador + op);
			bEstoque.setEnabled(true);

		} else {
			rotuloOperador += "Operador : ";
			loperador.setText(rotuloOperador + op);
			bEstoque.setEnabled(false);
		}
	}

	// Metodo que cria a janela e seus bot�es;
	public JanelaPrincipal() {
		// Configura��es do JFrame principal;
		super("Systek - Sistemas de Gerenciamento Inteligente");
		setLayout(null);
		setBackground(Color.BLACK);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setSize(1024, 768);
		setVisible(true);
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon(getClass().getResource("/imagens/Untitled-1.png")).getImage());
		
		// Inicia o metodo de privilegio administrativo
		liberaBotaoEstoque(JanelaPrincipal.operadorAtual);

		// Label estado caixa
		lEstadoCaixa.setFont(fontEstadoCaixa);
		lEstadoCaixa.setBounds(505, 115, 490, 80);
		lEstadoCaixa.setBackground(Color.BLACK);
		lEstadoCaixa.setForeground(Color.RED);
		lEstadoCaixa.setHorizontalAlignment(SwingConstants.CENTER);
		lEstadoCaixa.setOpaque(true);
		lEstadoCaixa.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		add(lEstadoCaixa);

		// Container que exibira dados dos produtos quando a compra for
		// efetuada;
		lContainer.setFont(fontContainer);
		lContainer.setVerticalAlignment(SwingConstants.NORTH);
		lContainer.setOpaque(true);
		lContainer.setHorizontalAlignment(SwingConstants.CENTER);
		lContainer.setBackground(Color.BLACK);
		lContainer.setForeground(Color.	WHITE);
		lContainer.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		lContainer.setVisible(true);

		// Scroll
		add(sContainer);
		sContainer.setBounds(505, 215, 490, 410);
		sContainer.setVisible(true);
		sContainer.setOpaque(true);

		// R�tulo Caixa que recebe o C�digo
		rCcodigo.setFont(fontLabelsCaixas);
		rCcodigo.setBounds(25, 100, 200, 80);
		rCcodigo.setHorizontalAlignment(SwingConstants.CENTER);
		rCcodigo.setOpaque(false);
		rCcodigo.setForeground(Color.WHITE);
		add(rCcodigo);

		// Caixa que recebe C�digo do Produto
		cCodProd.addActionListener(this);
		cCodProd.setFont(fontCaixasDeEntrada);
		cCodProd.setBounds(235, 115, 250, 50);
		cCodProd.setHorizontalAlignment(SwingConstants.CENTER);
		cCodProd.setOpaque(true);
		cCodProd.setBackground(Color.BLACK);
		cCodProd.setForeground(Color.WHITE);
		cCodProd.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		add(cCodProd);
		cCodProd.setEnabled(false);
		cCodProd.grabFocus();

		// R�tulo Caixa que recebe a quantidade
		rCquant.setFont(fontLabelsCaixas);
		rCquant.setBounds(60, 150, 200, 80);
		rCquant.setHorizontalAlignment(SwingConstants.CENTER);
		rCquant.setOpaque(false);
		rCquant.setForeground(Color.WHITE);
		add(rCquant);

		// Caixa que recebe a quantidade
		cQuant.addActionListener(this);
		cQuant.setFont(fontCaixasDeEntrada);
		cQuant.setBounds(235, 173, 250, 50);
		cQuant.setHorizontalAlignment(SwingConstants.CENTER);
		cQuant.setOpaque(true);
		cQuant.setBackground(Color.BLACK);
		cQuant.setForeground(Color.WHITE);
		cQuant.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		cQuant.setEnabled(false);
		add(cQuant);

		// R�tulo Caixa que exibe o valor unitario
		rCvalorUnit.setFont(fontLabelsCaixas);
		rCvalorUnit.setBounds(35, 230, 200, 50);
		rCvalorUnit.setHorizontalAlignment(SwingConstants.CENTER);
		rCvalorUnit.setOpaque(false);
		rCvalorUnit.setEnabled(false);
		rCvalorUnit.setForeground(Color.WHITE);
		add(rCvalorUnit);

		// Caixa que recebe o valor unitario
		cValorUnit.setFont(fontCaixasDeEntrada);
		cValorUnit.setBounds(235, 230, 250, 50);
		cValorUnit.setHorizontalAlignment(SwingConstants.CENTER);
		cValorUnit.setOpaque(true);
		cValorUnit.setBackground(Color.BLACK);
		cValorUnit.setForeground(Color.YELLOW);
		cValorUnit.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		cValorUnit.setEnabled(false);
		add(cValorUnit);

		// Caixa que recebe a descri��o do produto
		lDescricao.setFont(fontDescricao);
		lDescricao.setBounds(30, 290, 455, 120);
		lDescricao.setHorizontalAlignment(SwingConstants.CENTER);
		lDescricao.setOpaque(true);
		lDescricao.setBackground(Color.BLACK);
		lDescricao.setForeground(Color.YELLOW);
		lDescricao.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		add(lDescricao);

		// R�tulo Caixa que recebe o valor total
		rCvalorTotal.setFont(fontCaixasDeEntrada);
		rCvalorTotal.setBounds(505, 645, 237, 60);
		rCvalorTotal.setHorizontalAlignment(SwingConstants.CENTER);
		rCvalorTotal.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		rCvalorTotal.setBackground(Color.BLACK);
		rCvalorTotal.setOpaque(true);
		rCvalorTotal.setEnabled(false);
		rCvalorTotal.setForeground(Color.YELLOW);
		add(rCvalorTotal);

		// Label que exibe o valor Total
		lValorTotal.setFont(fontCaixasDeEntrada);
		lValorTotal.setBounds(757, 645, 237, 60);
		lValorTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lValorTotal.setOpaque(true);
		lValorTotal.setBackground(Color.BLACK);
		lValorTotal.setForeground(Color.GREEN);
		lValorTotal.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		lValorTotal.setEnabled(false);
		add(lValorTotal);
		
		// Mostra Data e Hora
		 ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	        scheduler.scheduleAtFixedRate(
	          new Runnable() {
	              public void run() {
	            	  Date d = new Date();  
	                  StringBuffer data = new StringBuffer(); 
	            	  SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");
	            	  data.append(sdfData.format(d));
	            	  data.append(" - ");  
	                  dataHora.setText(""+ data.toString() + new SimpleDateFormat("HH:mm:ss").format(new Date()));
	              }
	        }, 1, 1, TimeUnit.SECONDS);
	        
		dataHora.setFont(fontOperador);
		dataHora.setBounds(805,30,200,80);
		dataHora.setOpaque(false);
		dataHora.setForeground(Color.WHITE);
		add(dataHora);
		
		// Mostra o operador atual
		loperador.setFont(fontOperador);
		loperador.setBounds(488, 30, 200, 80);
		loperador.setOpaque(false);
		loperador.setForeground(Color.WHITE);
		add(loperador);

		// Logotipo da Tela de Caixa
		lLogotipo.setFont(fontOperador);
		lLogotipo.setBounds(37, 12, 440, 76);
		lLogotipo.setHorizontalAlignment(SwingConstants.CENTER);
		lLogotipo.setOpaque(false);
		lLogotipo.setForeground(Color.WHITE);
		lLogotipo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
		add(lLogotipo);

		// Configura��o do Bot�es

		// Bot�o que leva para a tela de gerenciamento de estoque
		bEstoque.addActionListener(this);
		bEstoque.setFont(fontBotoesSuperiores);
		bEstoque.setBounds(485, 12, 110, 20);
		bEstoque.setMnemonic(VK_F2);
		// bAdmin.setRequestFocusEnabled(false);
		bEstoque.setRolloverEnabled(false);
		add(bEstoque);

		// Bot�o que leva novamente para a tela de login para troca de usu�rio
		bTrocaUsuario.addActionListener(this);
		bTrocaUsuario.setFont(fontBotoesSuperiores);
		bTrocaUsuario.setBounds(600, 12, 154, 20);
		bTrocaUsuario.setMnemonic(VK_F3);
		// bTrocaUsuario.setRequestFocusEnabled(false);
		bTrocaUsuario.setRolloverEnabled(false);
		add(bTrocaUsuario);

		// Bot�o que exibe informa��es sobre o sistema
		bInfo.addActionListener(this);
		bInfo.setFont(fontBotoesSuperiores);
		bInfo.setBounds(760, 12, 110, 20);
		// bSobre.setRequestFocusEnabled(false);
		bInfo.setRolloverEnabled(false);
		add(bInfo);

		// Bot�o sair do sistema
		bSair.addActionListener(this);
		bSair.setFont(fontBotoesSuperiores);
		bSair.setBounds(875, 12, 110, 20);
		// bSair.setRequestFocusEnabled(false);
		bSair.setRolloverEnabled(false);
		add(bSair);

		// Bot�o excluir do carrinho
		bExclCarrinho.addActionListener(this);
		bExclCarrinho.setFont(fontContainer);
		bExclCarrinho.setBounds(30, 570, 220, 135);
		bExclCarrinho.setEnabled(false);
		add(bExclCarrinho);

		// Bot�o nova venda
		bFechaCaixa.setBackground(Color.WHITE);
		bFechaCaixa.setMnemonic(VK_Q);
		bFechaCaixa.addActionListener(this);
		bFechaCaixa.setBounds(265, 571, 220, 135);
		bFechaCaixa.setEnabled(false);
		bFechaCaixa.setFont(fontBotoesInferiores);
		add(bFechaCaixa);

		// Bot�o finalizar compra
		bFinalizarCompra.setBackground(Color.WHITE);
		bFinalizarCompra.setMnemonic(VK_W);
		bFinalizarCompra.addActionListener(this);
		bFinalizarCompra.setFont(fontContainer);
		bFinalizarCompra.setBounds(30, 420, 455, 135);
		bFinalizarCompra.setEnabled(false);
		add(bFinalizarCompra);

		// Label topo;
		lTopo.setBounds(4, 5, 1010, 95);
		lTopo.setBackground(new Color(0, 10, 50));
		lTopo.setOpaque(true);
		lTopo.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		add(lTopo);

		// Plano de Fundo
		lFundo.setBounds(1, 1, 1022, 730);
		lFundo.setVisible(true);
		add(lFundo);

		// Opera��o para Abrir o Caixa
		int bt = JOptionPane.YES_NO_OPTION;
		int choose = JOptionPane.showConfirmDialog(null, "Deseja Abrir o Caixa agora ?", "", bt, -1);

		if (choose == JOptionPane.YES_OPTION) {
			lEstadoCaixa.setText("CAIXA LIVRE");
			lEstadoCaixa.setForeground(Color.YELLOW);
			cCodProd.setEnabled(true);
			cCodProd.grabFocus();
			cCodProd.setText("");
			cQuant.setEnabled(true);
			bFechaCaixa.setText("FECHAR CAIXA");
			bFinalizarCompra.setEnabled(true);
			bExclCarrinho.setEnabled(true);
			bFechaCaixa.setEnabled(true);
			rCvalorTotal.setEnabled(true);
			cValorUnit.setEnabled(true);
			rCvalorUnit.setEnabled(true);
			lValorTotal.setEnabled(true);
			cValorUnit.setText(null);
			cQuant.setText(null);
			
			lContainer.setText("<html><br>" + "Aguardando Pr�xima Venda");
			lucroDoDia = 0.0;

			valorInicialCaixa = Double.parseDouble(
					JOptionPane.showInputDialog(null, "Digite o valor inicial em caixa", "Abertura de Caixa", -1));

			JOptionPane.showMessageDialog(null,
					"Caixa Aberto por " + operadorAtual + "\n" + "Valor em caixa :  R$ " + valorInicialCaixa,
					"Abertura de Caixa", -1);

		}
		if (choose == JOptionPane.NO_OPTION) {

			bFechaCaixa.setEnabled(true);
			bFechaCaixa.setText("ABRIR CAIXA");
			lEstadoCaixa.setText("CAIXA FECHADO");

		}

	}
}
