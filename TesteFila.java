import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TesteFila {

    public static void main(String[] args) throws InterruptedException, Fila.FilaVaziaException {
        Fila fila = new Fila();
        int opcao = -1;
        Scanner scanner = new Scanner(System.in);
        int contDocumentos = 0;

        while (opcao != 0) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Adicionar documento à fila de impressão");
            System.out.println("2. Consultar documento da fila");
            System.out.println("3. Imprimir documento da fila");
            System.out.println("0. Sair\n");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1://adicina doc
                    System.out.println("\nEnfileirar documento:\n");
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
                                fila.enfileira(new Documento(nomeArquivo, usuario));
                                contDocumentos++;
                                System.out.println("Documento '" + nomeArquivo + "' de '" + usuario + "' adicionado à fila");
                            } catch (Fila.FilaCheiaException e) {
                                System.out.println(e.getMessage());
                            }
                        }

                    } catch (FileNotFoundException e) {
                        System.out.println("Arquivo de texto não encontrado");
                    } finally {
                        if (arquivoScanner != null) {
                            arquivoScanner.close();
                        }
                    }
                    break;
                case 2://consulta docc
                    System.out.println(fila);
                    System.out.println("\nConsulta:\n");
                    System.out.println("Digite o nome do documento que deseja consultar (ex: exemplo.docx): ");
                    String consultaArquivo = scanner.nextLine();
                    fila.consultaDocumento(consultaArquivo);
                    break;

                case 3:
                //desenfileira e imprime
                //FIFO: o primeiro que entra é o primeiro que sai
                    System.out.println("\n\nDesenfileirar e imprimir:");
                    try {
                        Documento impresso = fila.desenfileira();
                        impresso.setHorarioImpressao(); 

                        System.out.println("Impresso: " + impresso.getNomeArquivo()
                                + ", Usuário: " + impresso.getUsuario() +  ", Solicitado: " + impresso.getHorarioSolicitacao()
                                + ", Impresso em: " + impresso.getHorarioImpressao()
                                + ", Tempo de Espera: " + impresso.getTempoEsperaSegundos() + "s\n");
                    } catch (Fila.FilaVaziaException e) {
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
