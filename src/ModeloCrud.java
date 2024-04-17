import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public interface ModeloCrud {
	
    String arqReader= "funcionarios.txt";
    
	public static void incluir(Funcionario funcionario) throws IOException {
		List<Funcionario> listFuncionarios = listar();
		List<Integer> listCodigos = new ArrayList<Integer>();
		
		StringBuilder sb = new StringBuilder();

		listFuncionarios.stream().forEach(action -> {
			int ultimoCodigo = action.getId();
			listCodigos.add(ultimoCodigo);
			sb.append(action.getId() + "|" + action.getNome() + "|" + action.getCidade() + "|" + action.getUF() + "|" + action.getStatus() + "|");
			sb.append(System.lineSeparator());
		});
		
		Long codigoId = listCodigos.stream().count();
		int codigo = codigoId.intValue();
		codigo++;
		sb.append(codigo + "|" + funcionario.getNome() + "|" + funcionario.getCidade() + "|" + funcionario.getUF() + "|" + "ATIVO|");
		FileWriter batWriter = new FileWriter(arqReader);
		PrintWriter gravaBat = new PrintWriter(batWriter);
		gravaBat.print(sb);
		gravaBat.close();
	}
	
	public static void alterar(Funcionario funcionario) throws IOException {
		List<Funcionario> listFuncionarios = listar();

		StringBuilder sb = new StringBuilder();
		
		listFuncionarios.stream().forEach(action -> {

			int codigo = action.getId();
			
			if (codigo == funcionario.getId()) {
				sb.append(codigo + "|" + funcionario.getNome() + "|" + funcionario.getCidade() + "|" + funcionario.getUF() + "|" + action.getStatus() + "|");
			}else {
				sb.append(action.getId() + "|" + action.getNome() + "|" + action.getCidade() + "|" + action.getUF() + "|" + action.getStatus() + "|");
				sb.append(System.lineSeparator());
			}
			
			FileWriter batWriter = null;
			try {
				batWriter = new FileWriter(arqReader);
			} catch (IOException e) {
				e.printStackTrace();
			}
			PrintWriter gravaBat = new PrintWriter(batWriter);
			gravaBat.print(sb);
			gravaBat.close();
			
		});

	}
	
	public static void excluir(Funcionario funcionario) throws IOException {

		List<Funcionario> listFuncionarios = listar();

		StringBuilder sb = new StringBuilder();

		listFuncionarios.stream().forEach(action -> {

			int codigo = action.getId();

			if (codigo == funcionario.getId()) {
				sb.append(codigo + "|" + funcionario.getNome() + "|" + funcionario.getCidade() + "|" + funcionario.getUF() + "|DESATIVADO|");
			} else {
				sb.append(action.getId() + "|" + action.getNome() + "|" + action.getCidade() + "|" + action.getUF() + "|" + action.getStatus() + "|");
				sb.append(System.lineSeparator());
			}

			FileWriter batWriter = null;
			try {
				batWriter = new FileWriter(arqReader);
			} catch (IOException e) {
				e.printStackTrace();
			}
			PrintWriter gravaBat = new PrintWriter(batWriter);
			gravaBat.print(sb);
			gravaBat.close();
		});
	}

	public static void recuperar(Funcionario funcionario) throws IOException {

		List<Funcionario> listFuncionarios = listar();

		StringBuilder sb = new StringBuilder();

		listFuncionarios.stream().forEach(action -> {

			int codigo = action.getId();

			if (codigo == funcionario.getId()) {
				sb.append(codigo + "|" + funcionario.getNome() + "|" + funcionario.getCidade() + "|" + funcionario.getUF() + "|ATIVO|");
			} else {
				sb.append(action.getId() + "|" + action.getNome() + "|" + action.getCidade() + "|" + action.getUF() + "|" + action.getStatus() + "|");
				sb.append(System.lineSeparator());
			}

			FileWriter batWriter = null;
			try {
				batWriter = new FileWriter(arqReader);
			} catch (IOException e) {
				e.printStackTrace();
			}
			PrintWriter gravaBat = new PrintWriter(batWriter);
			gravaBat.print(sb);
			gravaBat.close();
		});
	}

	public static List<Funcionario> recuperarLista() throws IOException {
		List<Funcionario> funcionariosTemp = new ArrayList<Funcionario>();

		List<Funcionario> listFuncionarios = listar();
		listFuncionarios.stream().forEach(action -> {
			Funcionario funcionarioTemp = new Funcionario();

			int codigo = action.getId();
			funcionarioTemp.setId(codigo);

			String nomeFuncionario = action.getNome();
			funcionarioTemp.setNome(nomeFuncionario);

			String cidadeFuncionario = action.getCidade();
			funcionarioTemp.setCidade(cidadeFuncionario);

			String ufFuncionario = action.getUF();
			funcionarioTemp.setUF(ufFuncionario);

			if (!action.getStatus().equalsIgnoreCase("ATIVO")) {
				funcionariosTemp.add(funcionarioTemp);
			}

		});

		return funcionariosTemp;
	}

	public static void validar() {

	}

	public static List<Funcionario> listar() throws IOException {
	 	FileReader fileReader = new FileReader(arqReader);
	 	BufferedReader lerArq = new BufferedReader(fileReader);
	 	List<String> funcionariosList = new ArrayList<String>();
	  	List<Funcionario> funcionarios = new ArrayList<Funcionario>();

	 	String line = lerArq.readLine();

	 	while (line != null) {
			 funcionariosList.add(line);
			 line = lerArq.readLine();
		}

	    lerArq.close();

	 	funcionariosList.stream().forEach(funcionario -> {
			Funcionario func = new Funcionario();

			String linhaCompleta = funcionario;
		 	int codigo = Integer.parseInt(linhaCompleta.substring(0, linhaCompleta.indexOf("|")));
		 	func.setId(codigo);

		 	linhaCompleta = linhaCompleta.replace(linhaCompleta.substring(0, linhaCompleta.indexOf("|"))+"|", "");
		 	String nome = linhaCompleta.substring(0, linhaCompleta.indexOf("|"));
		 	func.setNome(nome);

		 	linhaCompleta = linhaCompleta.replace(linhaCompleta.substring(0, linhaCompleta.indexOf("|"))+"|", "");
		 	String cidade = linhaCompleta.substring(0, linhaCompleta.indexOf("|"));
		 	func.setCidade(cidade);

		 	linhaCompleta = linhaCompleta.replace(linhaCompleta.substring(0, linhaCompleta.indexOf("|"))+"|", "");
		 	String uf = linhaCompleta.substring(0, linhaCompleta.indexOf("|"));
		 	func.setUF(uf);

		 	linhaCompleta = linhaCompleta.replace(linhaCompleta.substring(0, linhaCompleta.indexOf("|"))+"|", "");
		 	String status = linhaCompleta.substring(0, linhaCompleta.indexOf("|"));
		 	func.setStatus(status);

		 	funcionarios.add(func);

		 });

		return funcionarios;
	}

	static List<Funcionario> pesquisarNome(String nome) throws IOException {
		List<Funcionario> funcionariosTemp = new ArrayList<Funcionario>();

		List<Funcionario> listFuncionarios = listar();
		listFuncionarios.stream().forEach(action -> {
			Funcionario funcionarioTemp = new Funcionario();

			int codigo = action.getId();
			funcionarioTemp.setId(codigo);

			String nomeFuncionario = action.getNome();
			funcionarioTemp.setNome(nomeFuncionario);

			String cidadeFuncionario = action.getCidade();
			funcionarioTemp.setCidade(cidadeFuncionario);

			String ufFuncionario = action.getUF();
			funcionarioTemp.setUF(ufFuncionario);

			if (nomeFuncionario.contains(nome) && action.getStatus().equalsIgnoreCase("ATIVO")) {
				funcionariosTemp.add(funcionarioTemp);
			}

		});

		return funcionariosTemp;
	}



	public static Funcionario pesquisarCodigo(int codigoFuncionario) throws IOException {
		List<Funcionario> listFuncionarios = listar();
		Funcionario funcionarioTemp = new Funcionario();

		listFuncionarios.stream().forEach(action -> {
			int codigo = action.getId();

			if (codigo == codigoFuncionario) {
				funcionarioTemp.setId(codigo);

				String nomeFuncionario = action.getNome();
				funcionarioTemp.setNome(nomeFuncionario);

				String cidadeFuncionario = action.getCidade();
				funcionarioTemp.setCidade(cidadeFuncionario);

				String ufFuncionario = action.getUF();
				funcionarioTemp.setUF(ufFuncionario);
			}
		});

		return funcionarioTemp;
	}

}
