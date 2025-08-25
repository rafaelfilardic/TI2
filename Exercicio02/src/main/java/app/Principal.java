package app;

import java.util.List;
import java.util.Scanner;
import dao.UsuarioDAO;
import model.Usuario;

public class Principal {
	
	public static void main(String[] args) throws Exception {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Scanner scanner = new Scanner(System.in);
		int opcao = 0;
		
		System.out.println("Conexão com o banco de dados estabelecida.");

		do {
			System.out.println("\n--- MENU DE USUÁRIOS ---");
			System.out.println("1. Inserir");
			System.out.println("2. Listar");
			System.out.println("3. Atualizar");
			System.out.println("4. Excluir");
			System.out.println("5. Autenticar");
			System.out.println("6. Sair");
			System.out.print("Escolha uma opção: ");

			try {
				opcao = scanner.nextInt();
				scanner.nextLine();
			}
			catch(Exception e) {
				System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
				scanner.nextLine();
				opcao = 0;
				continue;
			}

			switch(opcao) {
				case 1:
					System.out.println("\n--- INSERIR USUÁRIO ---");
					System.out.print("Login: ");
					String loginInsert = scanner.nextLine();
					System.out.print("Senha: ");
					String senhaInsert = scanner.nextLine();
					System.out.print("Sexo (M/F): ");
					char sexoInsert = scanner.nextLine().toUpperCase().charAt(0);
					
					Usuario novoUsuario = new Usuario(-1, loginInsert, senhaInsert, sexoInsert);
					if(usuarioDAO.insert(novoUsuario)) {
						System.out.println("Usuário inserido com sucesso!");
					}
					else {
						System.out.println("Erro ao inserir usuário.");
					}
					break;
					
				case 2:
					System.out.println("\n--- LISTAR USUÁRIOS ---");
					List<Usuario> usuarios = usuarioDAO.getOrderByCodigo();
					if(usuarios.isEmpty()) {
						System.out.println("Nenhum usuário cadastrado.");
					}
					else {
						for(Usuario u : usuarios) {
							System.out.println(u.toString());
						}
					}
					break;
					
				case 3:
					System.out.println("\n--- ATUALIZAR USUÁRIO ---");
					System.out.print("Código do usuário a ser atualizado: ");
					int codigoUpdate = scanner.nextInt();
					scanner.nextLine();
					
					System.out.print("Novo login: ");
					String loginUpdate = scanner.nextLine();
					System.out.print("Nova senha: ");
					String senhaUpdate = scanner.nextLine();
					System.out.print("Novo sexo (M/F): ");
					char sexoUpdate = scanner.nextLine().toUpperCase().charAt(0);

					Usuario usuarioAtualizar = new Usuario(codigoUpdate, loginUpdate, senhaUpdate, sexoUpdate);
					if(usuarioDAO.update(usuarioAtualizar)) {
						System.out.println("Usuário atualizado com sucesso!");
					}
					else {
						System.out.println("Erro ao atualizar usuário.");
					}
					break;
					
				case 4:
					System.out.println("\n--- EXCLUIR USUÁRIO ---");
					System.out.print("Código do usuário a ser excluído: ");
					int codigoDelete = scanner.nextInt();
					scanner.nextLine();
					
					if(usuarioDAO.delete(codigoDelete)) {
						System.out.println("Usuário excluído com sucesso!");
					}
					else {
						System.out.println("Erro ao excluir usuário.");
					}
					break;
					
				case 5:
					System.out.println("\n--- AUTENTICAR USUÁRIO ---");
					System.out.print("Login: ");
					String loginAuth = scanner.nextLine();
					System.out.print("Senha: ");
					String senhaAuth = scanner.nextLine();
					
					if(usuarioDAO.autenticar(loginAuth, senhaAuth)) {
						System.out.println("Autenticação bem-sucedida!");
					}
					else {
						System.out.println("Login ou senha incorretos.");
					}
					break;
					
				case 6:
					System.out.println("Encerrando a aplicação...");
					break;
					
				default:
					System.out.println("Opção inválida. Digite um número de 1 a 6.");
					break;
			}
		} while(opcao != 6);
		
		scanner.close();
		usuarioDAO.close();
	}
}