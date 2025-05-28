import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestePilha {

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        Pilha pilha = new Pilha();
        int opcao = -1;
        Scanner scanner = new Scanner(System.in);
        int contDocumentos = 0;

        while (opcao != 0) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Adicionar documento à pilha de reimpressão");
            System.out.println("2. Consultar documento da pilha");
            System.out.println("3. Reimprimir documento da pilha");
            System.out.println("0. Sair\n");

            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1://empilha
                    System.out.println("Empilhar documento:\n");
                    Scanner arquivoScanner = null;
                    try {
                        arquivoScanner = new Scanner(new File("Entrada.txt"));
                        String nomeArquivo = null;
                        String usuario = null;
                        boolean temDocumento = false;

                        for (int i = 0; i < contDocumentos; i++) {
                            if (arquivoScanner.hasNextLine()) {
                                arquivoScanner.nextLine();
                            }
                            if (arquivoScanner.hasNextLine()) {
                                arquivoScanner.nextLine();
                            }
                        }
                        if (arquivoScanner.hasNextLine()) {
                            nomeArquivo = arquivoScanner.nextLine();
                            if (arquivoScanner.hasNextLine()) {
                                usuario = arquivoScanner.nextLine();
                                temDocumento = true;
                            } else {
                                System.out.println("Erro: conteúdo incompleto");
                            }
                        } else {
                            System.out.println("Todos os documentos já foram enfileirados ou o arquivo está vazio");
                        }
                        if (temDocumento) {
                            try {
                                pilha.push(new Documento(nomeArquivo, usuario));
                                contDocumentos++;
                                System.out.println("Documento '" + nomeArquivo + "' de '" + usuario + "' adicionado à pilha");
                            } catch (Pilha.PilhaCheiaException e) {
                                System.out.println(e.getMessage());
                            }
                            System.out.println(pilha);
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("Arquivo de texto não encontrado");
                    } finally {
                        if (arquivoScanner != null) {
                            arquivoScanner.close();
                        }
                    }
                    break;

                case 2://consulta
                    System.out.println(pilha);
                    System.out.println("\nConsulta:\n");
                    System.out.println("Digite o nome do arquivo que deseja consultar (ex: exemplo.docx): ");
                    String consultaArquivo = scanner.nextLine();
                    pilha.consultaDocumento(consultaArquivo);
                    break;

                case 3:
                    //desempilha e reimprime
                    //LIFO: o ultimo a entrar é o primeiro a sair
                    System.out.println("\nDesempilha e reimpreme:");
                    try {
                        Documento reimpresso = pilha.pop();
                        reimpresso.setHorarioImpressao();
                        System.out.println("Reimpresso: " + reimpresso.getNomeArquivo()
                                + ", Usuário: " + reimpresso.getUsuario() + ", Solicitado: " + reimpresso.getHorarioSolicitacao().toLocalTime()
                                + ", Reimpresso em: " + reimpresso.getHorarioImpressao().toLocalTime()
                                + ", Tempo de espera: " + reimpresso.getTempoEsperaSegundos() + "s\n");
                    } catch (Pilha.PilhaVaziaException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
        scanner.close();
    }
}
