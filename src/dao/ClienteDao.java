package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Cliente;

public class ClienteDao {
    private DataSource dataSource;
    
    public ClienteDao(DataSource dataSource){
        this.dataSource = dataSource;
    }
    
    public ArrayList<Cliente>readAll(){
        try{
            String SQL = "Select * From cliente";
            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            ArrayList<Cliente> lista = new ArrayList<Cliente>();
            while (rs.next()){
                Cliente cli = new Cliente();
                cli.setId(rs.getInt("id"));
                cli.setNome(rs.getString("nome"));
                cli.setEmail(rs.getString("email"));
                cli.setTelefone(rs.getNString("telefone"));
                lista.add(cli);
            }
            ps.close();
            return lista;
        }
        catch(SQLException ex){
            System.out.println("erro" + ex.getMessage());
        }return null;
    }
    
    public boolean inserirCliente(Cliente c){       
        boolean retorno;
        try{
            String sql = "INSERT INTO cliente("
                    + "nome, "
                    + "email, "
                    + "telefone) VALUES"
                    + "(?,?,?);";
            PreparedStatement insert = dataSource.getConnection().prepareStatement(sql);
            insert.setString(1, c.getNome());
            insert.setString(2, c.getEmail());
            insert.setString(3, c.getTelefone());
            
            insert.executeUpdate();
            insert.close();
            retorno = true;
        } catch (SQLException ex){
            ex.printStackTrace();
            retorno = false;
        }
        return retorno;

    }
    
    public boolean alterarCliente(Cliente c){
        boolean retorno;
        try{
            String sql = "UPDATE cliente SET "
                    + "nome = ?,"
                    + "email = ?,"
                    + "telefone = ?,"
                    + "WHERE id = ?";
            PreparedStatement update = dataSource.getConnection().prepareStatement(sql);
            update.setString(1, c.getNome());
            update.setString(2, c.getEmail());
            update.setString(3, c.getTelefone());
            update.setInt(4, c.getId());
            
            update.executeUpdate();
            update.close();
            retorno = true;
        } catch(SQLException ex){
            ex.printStackTrace();
            retorno = false;
        } 
        return retorno;
    }
    
    public boolean excluirCliente(int id){
        boolean retorno;
        try{
            String sql = "DELETE FROM cliente WHERE id = ?";
            PreparedStatement delete = dataSource.getConnection().prepareStatement(sql);
            delete.setInt(1, id);
            
            delete.executeUpdate();
            delete.close();
            retorno = true;
        } catch(SQLException ex){
            ex.printStackTrace();
            retorno = false;
        }
        return retorno;
}
}
