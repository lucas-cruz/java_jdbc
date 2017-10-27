
import dao.ClienteDao;
import dao.DataSource;
import model.Cliente;

public class Teste {

public static void main(String[] args) {
   DataSource dc = new DataSource();
   ClienteDao Banco = new ClienteDao(dc);
   Cliente Ricardo = new Cliente();
   Ricardo(1);
   Ricardo.setNome("Ricardo");
   Ricardo.setEmail("exemplo@exemplo.com");
   Ricardo.setTelefone("123456789");
   Banco.inserirCliente(Ricardo);
   dc.closeConnection();
}
    
}
