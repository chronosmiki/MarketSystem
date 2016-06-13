package br.com.system;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;

  		/*Classe DBProdutos resposavel pela criação da conexão com o Banco de Dados JDBC
		*O banco de dados usado é o Derby. 
       *Ela também extende JFrame pois usamos para uma janela de loading.
       */
        public class DbProdutos extends JFrame  {
        	
        	static boolean conectado = false;
        
  			private static final long serialVersionUID = 1L;
			JFrame Db ;
        	Font caixaBd = new Font ("Arial",  Font.BOLD, 18);
        	JLabel rConectaBd = new JLabel("Conectando ao Banco de Dados ... ");
              			
        	private static String dbURL = "jdbc:derby:C:/Systek/MyDB;create=true;user=vitor;password=123";
        	private static String Produtos = "DBESTOQUE.PRODUTOS";
        	        	
        	//jdbc Connection
        	private static Connection conn = null;
        	private static Statement stmt = null;
	
        	static int codigo = 1;    
        	
        	//Decimal format usado para definir o retorno dos metodos do BD.
        	static DecimalFormat df = new DecimalFormat("#,###.00");
        	
        	//Metodo que retorna valor total do em Estoque.       	
        	public String returnValorEstoque()
        	{  
        		
        		double soma = 0.0;
            
                for(int i=1; i<DbProdutos.codigo;i++){	    	    	
                soma += buscaPreco(i)* buscaQuant(i); 	
            }
                
            String dz = "R$ " + df.format(soma);   
            	return dz;     	 			 	  
        }
  
	//****************************************************************
        //Metodo de conexão com o BD;
    
	 static void criaConexao()
	{
       do{
		try 
		     {
	         	Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance(); //Codigo que seta o driver do sgbd;
	         	//Conectando...
	         	conn = DriverManager.getConnection(dbURL); //Variavel conn, que tem a Conex?o, recebe a conex?o com o URL;
		       }
		
		catch (Exception except)
			{
               except.printStackTrace(); //Erro caso não ocorra a conexão
			  }
		    	conectado = true;
		    	JanelaPrincipal.lDescricao.setText("Descrição do Produto");
		       }
       while (conectado == false);
         
	}	
    
	//Metódo para a inserção de dados no BD. 
	static void insereDados(String nome, double preco, int quantidade)
	{
		try {
                    stmt = conn.createStatement();
                    PreparedStatement pstmt = conn.prepareStatement("insert into " + Produtos + " (Nome, Preço, Quant) values (?,?,?)"); 
			
                    pstmt.setString(1, nome);
                    pstmt.setDouble(2, preco);
                    pstmt.setInt(3, quantidade);
                    
                    
                    pstmt.executeUpdate();
			
                    stmt.close();
		}
		catch (SQLException sqlExcept)
		{
                    sqlExcept.printStackTrace();
		}
	}
   
	 //Retornar todos os produtos   
	static String buscaTodos()
	{
		String dados = "";
		try {
		    stmt = conn.createStatement();
		    ResultSet results = stmt.executeQuery("select * from " + Produtos);
					
			while(results.next())
			{
			    int id = results.getInt(1);
                String nome = results.getString(2);
                double preco = results.getDouble(3);
                int quantidade = results.getInt(4);
                
                dados +="<html>&nbsp;" + "Cod - " + id + " Nome - " + nome + " Preço - R$ " + df.format(preco) + " Quantidade - " + quantidade + "<br>";
            }
			results.close();
			stmt.close();
		}
		catch (SQLException sqlExcept)
		{
			sqlExcept.printStackTrace();
		}
			return dados;	
     }
        
         
	//Retorna preço do produto atraves do Cod_Produto
	static double buscaPreco(int id){
		double preco=0.0;
		try {			
		    stmt = conn.createStatement();
		    ResultSet results = stmt.executeQuery("select Preço from " + Produtos + " where " + "ID " + " = " + id);
		    while(results. next ())	{   				
		    preco = results.getDouble(1);	
		    }
		   
		    results.close();
		    stmt.close();
		}
		catch (SQLException sqlExcept)
		{
			sqlExcept.printStackTrace();
		}
	        return preco;
	}
    
	//Metodo que mostra quais produtos tem uma quantidade baixa em estoque.
	static String buscaEstoqueBaixo()
	{
		String quant = "";
		try {			
		    stmt = conn.createStatement();
		    ResultSet results = stmt.executeQuery("select Nome, Quantidade from " + Produtos + " where " + "Quant < 5");
		    
		    while(results.next())
			{	   				
		    	String nome = results.getString(1);
		    	int valor = results.getInt(2);				
		    	quant += nome + " - Quant - " + valor;
			}
		    results.close();
		    stmt.close();
		}
		catch (SQLException sqlExcept)
		{
			sqlExcept.printStackTrace();
		}
	        return quant;
	}
        
        
	//Retornar a quantidade de um produto através do ID    
	static int buscaQuant(int id)
	{
		int quant = 0;
		try
		{	
		    stmt = conn.createStatement();
		    ResultSet results = stmt.executeQuery("select Quantidade from " + Produtos + " where " + "ID " + " = " + id);
											
			while(results.next())
			{
			   quant = results.getInt(1);
			}
                        
			results.close();
			stmt.close();
		}
		catch (SQLException sqlExcept)
		{
		    sqlExcept.printStackTrace();
		}
	        return quant;
	}
	
    //Metodo que busca o nome de um produto de acordo com sua ID.    
	static String buscaNome(int id)
	{
		String nome = "";
		try
		{	
		    stmt = conn.createStatement();
		    ResultSet results = stmt.executeQuery("select Nome from " + Produtos + " where " + "ID " + " = " + id);
											
			while(results.next())
			{
			   nome = results.getString(1);
			}
                        
			results.close();
			stmt.close();
		}
		catch (SQLException sqlExcept)
		{
		    sqlExcept.printStackTrace();
		}
	        return nome;
	}
	
        
	//Retornar os dados de um produto através do ID
	static String buscaDado(int id)	{
		String dados="";
		
		try {
		    stmt = conn.createStatement();
		    ResultSet results = stmt.executeQuery("select * from " + Produtos + " where " + "ID" + " = " + id);		
		    int cod =0;
		    String nome="";
			
			double preco=0.0;		
				
			if(results.next() == true)
			{
			    cod = results.getInt(1);
			    nome = results.getString(2);
			    preco = results.getDouble(3);
			  							
			    dados = "Cod - " + cod + "  -  " + nome +
			            "  - Preço Unit R$ " + df.format(preco);
			}
			else
			{
				dados = "PRODUTO NÃO ENCONTRADO";
			}
				
			   results.close();
			   stmt.close();
		}
		catch (SQLException sqlExcept)
		{
			sqlExcept.printStackTrace();
		}
		    return dados;
	}
	   
	//Metodo que deleta um produto de acordo com sua ID	
	static void deletaDado(int id){
		try {
                PreparedStatement ps = conn.prepareStatement("DELETE FROM " + Produtos + " WHERE ID = ? ");
				ps.setInt(1, id);  
                ps.executeUpdate(); 
						
                ps.close();
		}
		catch (SQLException sqlExcept)
		{
			sqlExcept.printStackTrace();
		}
	}
	
             
	 //Atualiza os dados de um produto determinado pela sua ID, podemos alterar, nome, preço e quantidade em estoque.   
	static void atualizaDado(int id, String nome, double preco, int quantidade){
						
		try {
                PreparedStatement pstmt = conn.prepareStatement("update " + Produtos + " set" +
                " Nome = ?, Preço = ?, Quant = ? where ID = " + id); 
                
                 pstmt.setString(1, nome);
                 pstmt.setDouble(2, preco);
                 pstmt.setInt(3, quantidade);

                 pstmt.executeUpdate();
                               
                 pstmt.close();	   
		}
		catch (SQLException sqlExcept)
		{
			sqlExcept.printStackTrace();
		}
			
	}
	       
	// Finalizar conexão ao BD    
	static void finalizaBD(){
		try{
		    if (stmt != null){
                            stmt.close();
		    }
		    if (conn != null){
                        DriverManager.getConnection(dbURL + ";shutdown=true");
                        conn.close();
                    }
		}
		catch (SQLException sqlExcept)
		{
			sqlExcept.printStackTrace();
	    }
	}		
         
	}
        

