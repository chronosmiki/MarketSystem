package br.com.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
	/* JanelaEstoque é uma classe que cria a uma janela para manusearmos o Estoque no banco de dados.
	 * ela é responsavel por cadastrar os produtos, atualiza-los, removê-los e efetuar buscas no banco de dados
	 * com ajuda da interface grafica.
	 */
	public class JanelaEstoque extends JFrame
	implements ActionListener
	{
		
	
		private static final long serialVersionUID = 3L;
		
		ImageIcon planoFundo = new ImageIcon(getClass().getResource("/imagens/fundo.jpg"));
		Font fontBotoesInferiores = new Font("Arial", Font.BOLD, 16);
		Font fontlInfo = new Font("Arial", Font.BOLD, 30);
		
		JFrame jEstoque;
		JButton bAdd, bRemove, bAtualizaProd, bAtualiza, bVolta, bOk, bOk1, bOk2, bSair;
		JLabel lId, lNome, lBusca, lPreco, lQuant, lContainer, lInfo;
		JLabel lFundo = new JLabel(planoFundo);
		JTextField tId, tNome, tPreco, tQuant, tBusca;
		JScrollPane jsp;
		
		String dados = "";
		int id = 0;
		
		// Metodo que cria a Janela grafica, seus botões e labels.
		public JanelaEstoque()
		{
			
			jEstoque = new JFrame("Controle de Estoque");
			jEstoque.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			jEstoque.setBackground(Color.BLACK);
			jEstoque.setLayout(null);
			
									
			lContainer = new JLabel();
			lContainer.setVisible(true);
			lContainer.setOpaque(true);
			lContainer.setVerticalAlignment(SwingConstants.NORTH);
			lContainer.setHorizontalAlignment(SwingConstants.LEFT);
			lContainer.setLayout(null);
			lContainer.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			lContainer.setBackground(Color.WHITE);
			lContainer.setText(DbProdutos.buscaTodos());
			jsp = new JScrollPane(lContainer);
			jsp.setBounds(10,10,565,270);
			jsp.setVisible(true);
			
			lId = new JLabel("Código do Produto");
			lId.setBounds(10,10,140,40);
			lId.setLayout(null);
			lId.setForeground(Color.WHITE);
			lId.setVisible(false);
			
			lNome = new JLabel("Nome do Produto:");
			lNome.setBounds(10,50,140,40);
			lNome.setLayout(null);
			lNome.setForeground(Color.WHITE);
			lNome.setVisible(false);
			
			
			lPreco = new JLabel("Preço do Produto:");
			lPreco.setBounds(10,90,140,40);
			lPreco.setLayout(null);
			lPreco.setForeground(Color.WHITE);
			lPreco.setVisible(false);
			
			
			lQuant = new JLabel("Quantidade em Estoque:");
			lQuant.setBounds(10,130,140,40);
			lQuant.setLayout(null);
			lQuant.setForeground(Color.WHITE);
			lQuant.setVisible(false);
			
			lBusca = new JLabel("Buscar produto no estoque:");
			lBusca.setBounds(10,310,160,40);
			lBusca.setLayout(null);
			lBusca.setForeground(Color.WHITE);
			lBusca.setVisible(true);
			
			lInfo = new JLabel();
			lInfo.setBounds(160,210,400,40);
			lInfo.setLayout(null);
			lInfo.setForeground(Color.YELLOW);
			lInfo.setVisible(false);
			lInfo.setFont(fontlInfo);
			
			tId = new JTextField();
			tId.setBounds(155,15,160,32);
			tId.addActionListener(this);
			tId.setLayout(null);
			tId.setVisible(false);
			
			tNome = new JTextField();
			tNome.setBounds(155,55,300,32);
			tNome.setLayout(null);
			tNome.setVisible(false);
			
			
			tPreco = new JTextField();
			tPreco.setBounds(155,95,300,32);
			tPreco.setLayout(null);
			tPreco.setVisible(false);
			
			
			tQuant = new JTextField();
			tQuant.setBounds(155,135,300,32);
			tQuant.setLayout(null);
			tQuant.setVisible(false);
			
			
			tBusca = new JTextField();
			tBusca.setBounds(175,315,160,32);
			tBusca.setLayout(null);
			tBusca.addActionListener(this);
			tBusca.setVisible(true);
			
			
			
			bAdd = new JButton("Adicionar");
			bAdd.setBounds(15,365,120,40);
			bAdd.setLayout(null);
			bAdd.setToolTipText("Adicionar produtos ao estoque");
			bAdd.setMnemonic('A');
			bAdd.setFont(fontBotoesInferiores);
			bAdd.setBackground(Color.WHITE);
			bAdd.setBorder(BorderFactory.createLineBorder(Color.BLUE));  
			bAdd.addActionListener(this);
			bAdd.setVisible(true);
			
			
			bRemove = new JButton("Remover");
			bRemove.setBounds(305,365,120,40);
			bRemove.setLayout(null);
			bRemove.setToolTipText("Remover um produto do estoque");
			bRemove.setMnemonic('R');
			bRemove.setFont(fontBotoesInferiores);
			bRemove.setBackground(Color.WHITE);
			bRemove.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			bRemove.addActionListener(this);
			bRemove.setVisible(true);
			
			bAtualiza = new JButton("Atualizar Lista");
			bAtualiza.setBounds(435,315,120,32);
			bAtualiza.setLayout(null);
			bAtualiza.setToolTipText("Atualiza a lista de produtos exibida");
			bAtualiza.setMnemonic('L');
			bAtualiza.setFont(fontBotoesInferiores);
			bAtualiza.setBackground(Color.WHITE);
			bAtualiza.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			bAtualiza.addActionListener(this);
			bAtualiza.setVisible(true);
						
			bAtualizaProd = new JButton("Alterar Produto");
			bAtualizaProd.setBounds(145,365,150,40);
			bAtualizaProd.setLayout(null);
			bAtualizaProd.setToolTipText("Atualiza um produto do estoque");
			bAtualizaProd.setMnemonic('P');
			bAtualizaProd.setFont(fontBotoesInferiores);
			bAtualizaProd.setBackground(Color.WHITE);
			bAtualizaProd.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			bAtualizaProd.addActionListener(this);
			bAtualizaProd.setVisible(true);
			
			bOk = new JButton("Gravar");
			bOk.setBounds(15,365,120,40);
			bOk.setLayout(null);
			bOk.setToolTipText("Grava o novo produto no estoque");
			bOk.setMnemonic('G');
			bOk.setFont(fontBotoesInferiores);
			bOk.setBackground(Color.WHITE);
			bOk.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			bOk.addActionListener(this);
			bOk.setVisible(false);
			
			bOk1 = new JButton("Gravar");
			bOk1.setBounds(15,365,120,40);
			bOk1.setLayout(null);
			bOk1.setToolTipText("Grava o novo produto no estoque");
			bOk1.setMnemonic('G');
			bOk1.setFont(fontBotoesInferiores);
			bOk1.setBackground(Color.WHITE);
			bOk1.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			bOk1.addActionListener(this);
			bOk1.setVisible(false);
			
			bVolta = new JButton("Voltar");
			bVolta.setBounds(145,365,120,40);
			bVolta.setLayout(null);
			bVolta.setToolTipText("Volta ao controle de caixa");
			bVolta.setMnemonic('V');
			bVolta.setFont(fontBotoesInferiores);
			bVolta.setBackground(Color.WHITE);
			bVolta.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			bVolta.addActionListener(this);
			bVolta.setVisible(false);
			
			bSair = new JButton("Sair");
			bSair.setBounds(435,365,120,40);
			bSair.setLayout(null);
			bSair.setToolTipText("Volta ao controle de caixa");
			bSair.setMnemonic('S');
			bSair.setFont(fontBotoesInferiores);
			bSair.setBackground(Color.WHITE);
			bSair.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			bSair.addActionListener(this);
			bSair.setVisible(true);
			
			lFundo.setBounds(1, 1, 600, 450);
			lFundo.setVisible(true);
			
			
			jEstoque.add(jsp);
			jEstoque.add(lNome);
			jEstoque.add(lPreco);
			jEstoque.add(lQuant);
			jEstoque.add(lBusca);
			jEstoque.add(lId);
			
			jEstoque.add(tId);
			jEstoque.add(tNome);
			jEstoque.add(tPreco);
			jEstoque.add(tQuant);
			jEstoque.add(tBusca);
			
			jEstoque.add(bAdd);
			jEstoque.add(bRemove);
			jEstoque.add(bAtualizaProd);
			jEstoque.add(bVolta);
			jEstoque.add(bOk);
			jEstoque.add(bOk1);
			jEstoque.add(bSair);
			jEstoque.add(bAtualiza);
			jEstoque.add(lInfo);
			jEstoque.add(lFundo);
			
			jEstoque.setSize(600,450);
			jEstoque.setLocationRelativeTo(null);
			jEstoque.setVisible(true);
		
		}
		
		// Metodo que seta as ações de cada botão e caixa de texto, também faz alterações no layout da janela.				
		public void actionPerformed(ActionEvent acesso)
		{
			if(acesso.getSource() == bAdd)
			{
				
				jsp.setVisible(false);
				bAdd.setVisible(false);
				bRemove.setVisible(false);
				bAtualiza.setVisible(false);
				bAtualizaProd.setVisible(false);
				tBusca.setVisible(false);
				lBusca.setVisible(false);
				lInfo.setVisible(false);
				bVolta.setVisible(true);
				bSair.setVisible(false);
				bOk.setVisible(true);
				lNome.setVisible(true);
				lPreco.setVisible(true);
				lQuant.setVisible(true);
				tNome.setVisible(true);
				tPreco.setVisible(true);
				tQuant.setVisible(true);
																				
			}
			
			if(acesso.getSource() == bOk)
			{
				
				String nome = tNome.getText();
				double preco = Double.parseDouble(tPreco.getText());
				int quant = Integer.parseInt(tQuant.getText());		
				DbProdutos.insereDados(nome, preco, quant);
				JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso", "Confirmação", 1);
				tNome.setText(null);
				tQuant.setText(null);
				tPreco.setText(null);
		        tNome.grabFocus();
				
			}
			
			if(acesso.getSource() == bAtualiza)
			{
				lContainer.setText(DbProdutos.buscaTodos());
			}
			
			if(acesso.getSource() == bVolta)
			{
				
				jsp.setVisible(true);
				bAdd.setVisible(true);
				bRemove.setVisible(true);
				bAtualiza.setVisible(true);
				bAtualizaProd.setVisible(true);
				bSair.setVisible(true);
				tBusca.setVisible(true);
				lBusca.setVisible(true);
				lId.setVisible(false);
				tId.setVisible(false);
				bVolta.setVisible(false);
				bOk.setVisible(false);
				lNome.setVisible(false);
				lPreco.setVisible(false);
				lQuant.setVisible(false);
				tNome.setVisible(false);
				tPreco.setVisible(false);
				tQuant.setVisible(false);
				lInfo.setVisible(false);
			}
			
			if(acesso.getSource() == tBusca)
			{
				dados = null;
				int id = Integer.parseInt(tBusca.getText());
				tBusca.setText(null);
				dados = "<html>&nbsp;" + DbProdutos.buscaDado(id) + "<br>";
				lContainer.setText(dados);
								
			}
			
			if(acesso.getSource() == bAtualizaProd)
			{
				jsp.setVisible(false);
				bAdd.setVisible(false);
				bOk1.setVisible(true);
				bRemove.setVisible(false);
				bAtualiza.setVisible(false);
				bAtualizaProd.setVisible(false);
				bVolta.setVisible(true);
				bSair.setVisible(false);				
				lNome.setVisible(true);
				lPreco.setVisible(true);
				lBusca.setVisible(false);
				tBusca.setVisible(false);
				lQuant.setVisible(true);
				lInfo.setVisible(true);
				lId.setVisible(true);
				tId.setVisible(true);
				tNome.setVisible(true);
				tPreco.setVisible(true);
				tQuant.setVisible(true);
			}
				
				if(acesso.getSource() == tId)
				{
					lInfo.setText(DbProdutos.buscaNome(Integer.parseInt(tId.getText())));
					tNome.grabFocus();
				}
			
		
			if(acesso.getSource() == bOk1)
			{	
							
				String nome = tNome.getText();
				double preco = Double.parseDouble(tPreco.getText());
				int quant = Integer.parseInt(tQuant.getText());
				id = Integer.parseInt(tId.getText());
				DbProdutos.atualizaDado(id, nome, preco, quant);
				dados += "<html>&nbsp;" + DbProdutos.buscaDado(id) + "<br>";
				JOptionPane.showMessageDialog(null, "Produto alterado com sucesso", "Confirmação", 1);
				tId.setText(null);
				tNome.setText(null);
				tQuant.setText(null);
				tPreco.setText(null);
		        tId.grabFocus();
			}
		
			if(acesso.getSource() == bRemove)
			{
				id = Integer.parseInt(JOptionPane.showInputDialog(null,"Qual o código do produto que quer excluir?", "Excluir Produto",3));
				int opcao = JOptionPane.showConfirmDialog(null,"Deseja mesmo excluir o Produto: " + DbProdutos.buscaNome(id) + " ?", "Excluir Produto", JOptionPane.OK_CANCEL_OPTION);
				
				if(opcao == JOptionPane.OK_OPTION)
				{
					DbProdutos.deletaDado(id);
					lContainer.setText("<html>&nbsp;" + "Produto deletado com sucesso!" + "<br>");
				}
				
			}
		
			if(acesso.getSource() == bSair)
			{
				jEstoque.dispose();
			}
		
		}
		
	}

	