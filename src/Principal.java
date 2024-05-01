import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Principal {

	public static void main(String[] args) throws IOException {
		
		boolean running = true;
		Integer resultadoNumero = null;
		int idAutoIncremental = 0;

		InputStreamReader input = new InputStreamReader(System.in); 
        BufferedReader reader = new BufferedReader(input); 

        String arqReader= "funcionarios.txt";
		if(!new File(arqReader).exists()) {
			final FileWriter arqWriter = new FileWriter(arqReader);
		}
        
		while (running == true) {

			System.out.println("Olá, oque deseja realizar?");
			System.out.println("1-Inserir novo funcionário?");
			System.out.println("2-Alterar funcionário existente?");
			System.out.println("3-Excluir funcionário?");
			System.out.println("4-Recuperar funcionário apagado?");
			System.out.println("5-Listar todos funcionário?");
			System.out.println("9-Sair");

	        String option = reader.readLine();

			resultadoNumero = Integer.valueOf(String.valueOf(option));
			
			if (resultadoNumero == 1) {
				idAutoIncremental++;
				String nome = "";
				String cidade = "";
				String uf = "";
				Funcionario funcionarioNew = new Funcionario();
				System.out.println("Digite o Nome do funcionario: ");
				nome = reader.readLine();
				
				System.out.println("Digite a Cidade: ");
				cidade = reader.readLine();

				System.out.println("Digite o UF: ");
				uf = reader.readLine();
				
				funcionarioNew.setId(idAutoIncremental);
				funcionarioNew.setNome(nome);
				funcionarioNew.setCidade(cidade);
				funcionarioNew.setUF(uf);
				
				ModeloCrud.incluir(funcionarioNew);
				
			}else if(resultadoNumero == 2) {
				System.out.println("Antes é necessário encontrar o funcionário. ");
				System.out.println("Digite o nome dele:");
		        String nomeAProcurar = reader.readLine();
		        List<Funcionario> listFuncionarios = ModeloCrud.pesquisarNome(nomeAProcurar);
		        if (!listFuncionarios.isEmpty()) {
					System.out.println("Segue dados dos funcionarios encontrados, digite agora o código do qual deseja realizar a alteração");
					listFuncionarios.stream().forEach(action -> {
						System.out.println("Codigo:"+ action.getId());
						System.out.println("Nome:"+ action.getNome());
						System.out.println("Cidade:"+ action.getCidade());
						System.out.println("UF:"+ action.getUF());
					});
					System.out.println("Digite o código do funcionario que deseja alterar:");
			        String codigoStr = reader.readLine();
					int codigo = Integer.parseInt(String.valueOf(codigoStr));
					Funcionario funcionario = ModeloCrud.pesquisarCodigo(codigo);
					int idFuncionario = funcionario.getId();
					
					System.out.println("O Nome atual é:" + funcionario.getNome() + " deseja alterar para?");
			        String novoNomeFuncionario = reader.readLine();
					System.out.println("A Cidade atual é:" + funcionario.getCidade() + " deseja alterar para?");
			        String novoCidadeFuncionario = reader.readLine();
					System.out.println("A UF atual é:" + funcionario.getUF() + " deseja alterar para?");
			        String novoUFFuncionario = reader.readLine();
			        
			        Funcionario funcionarioParaAlterar = new Funcionario();
			        funcionarioParaAlterar.setCidade(novoCidadeFuncionario);
			        funcionarioParaAlterar.setId(idFuncionario);
			        funcionarioParaAlterar.setNome(novoNomeFuncionario);
			        funcionarioParaAlterar.setUF(novoUFFuncionario);
			        
			        ModeloCrud.alterar(funcionarioParaAlterar);

					System.out.println("Usuário alterado com sucesso!");
					
				}else {
					System.out.println("Nenhum funcionario encontrado com esse nome");
				}
				
			}else if(resultadoNumero == 3) {

				System.out.println("Antes é necessário encontrar o funcionário. ");
				System.out.println("Digite o nome dele:");
		        String nomeAProcurar = reader.readLine();
		        List<Funcionario> listFuncionarios = ModeloCrud.pesquisarNome(nomeAProcurar);
		        if (!listFuncionarios.isEmpty()) {
					System.out.println("Segue dados dos funcionarios encontrados, digite agora o código do qual deseja realizar a exclusão");
					listFuncionarios.stream().forEach(action -> {
						System.out.println("Codigo:"+ action.getId());
						System.out.println("Nome:"+ action.getNome());
						System.out.println("Cidade:"+ action.getCidade());
						System.out.println("UF:"+ action.getUF());
					});
					System.out.println("Digite o código do funcionario que deseja excluir:");
			        String codigoStr = reader.readLine();
					int codigo = Integer.parseInt(String.valueOf(codigoStr));
					Funcionario funcionario = ModeloCrud.pesquisarCodigo(codigo);
					int idFuncionario = funcionario.getId();
					
			        ModeloCrud.excluir(funcionario);

					System.out.println("Usuário excluido com sucesso!");
					
				}else {
					System.out.println("Nenhum funcionario encontrado com esse nome");
				}
				
			}else if(resultadoNumero == 4) {
                System.out.println("Segue Funcionários Excluídos/Deletados!");
                System.out.println("ID  |  NOME  |  CIDADE  |  UF  |");
                List<Funcionario> funcionarios = ModeloCrud.recuperarLista();
                funcionarios.stream().forEach(action -> {
                    System.out.println(action.getId() + " | " + action.getNome() + " | " + action.getCidade() + " | " + action.getUF() + " | ");
                });
                System.out.println("Digite o ID de qual deseja Recuperar:");

                String codigoStr = reader.readLine();
                int codigo = Integer.parseInt(String.valueOf(codigoStr));
                Funcionario funcionario = ModeloCrud.pesquisarCodigo(codigo);
                int idFuncionario = funcionario.getId();

                ModeloCrud.recuperar(funcionario);

                System.out.println("Usuário recuperado com sucesso!");
			}else if(resultadoNumero == 5) {
				System.out.println("Segue Funcionários cadastrados!");
				System.out.println("ID  |  NOME  |  CIDADE  |  UF  |");
				List<Funcionario> funcionarios = ModeloCrud.listar();
				funcionarios.stream().forEach(action -> {
					if(action.getStatus().equalsIgnoreCase("ATIVO")){
						System.out.println(action.getId() + " | " + action.getNome() + " | " + action.getCidade() + " | " + action.getUF() + " | ");
					}
				});
			}else if(resultadoNumero == 9) {
				running = false;
			}
			
		}
		
		System.out.println("Sessão finalizada!");
	}
}
