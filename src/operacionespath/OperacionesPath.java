package operacionespath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

public class OperacionesPath {

    public static void main(String[] args) throws IOException {
        Path sourceDirectory = Paths.get("C:\\Users\\cesar\\Desktop\\pruebas\\tmp\\niats"); // Reemplaza con la ruta de tu directorio
        Files.createDirectories(sourceDirectory);

        Path directoryAAA = Paths.get(sourceDirectory.toString(), "aaa");
        Path directoryBBB = Paths.get(directoryAAA.toString(), "bbb");
        Path directoryCCC = Paths.get(directoryAAA.toString(), "ccc");
        Path directoryDDD = Paths.get(directoryCCC.toString(), "ddd");
        Path directoryEEE = Paths.get(directoryAAA.toString(), "eee");
        Path directoryFFF = Paths.get(sourceDirectory.toString(), "fff");

        Path fileA = Paths.get(directoryAAA.toString(), "a.txt");
        Path fileB = Paths.get(directoryBBB.toString(), "b.txt");
        Path fileC = Paths.get(directoryCCC.toString(), "c.txt");
        Path fileD = Paths.get(directoryDDD.toString(), "d.txt");
        Path fileE = Paths.get(directoryEEE.toString(), "e.txt");
        Path fileF = Paths.get(directoryFFF.toString(), "f.txt");
        Path fileN = Paths.get(sourceDirectory.toString(), "n.txt");

        Files.createDirectories(directoryAAA);
        Files.createDirectories(directoryBBB);
        Files.createDirectories(directoryCCC);
        Files.createDirectories(directoryDDD);
        Files.createDirectories(directoryEEE);
        Files.createDirectories(directoryFFF);

        Files.createFile(fileA);
        Files.createFile(fileB);
        Files.createFile(fileC);
        Files.createFile(fileD);
        Files.createFile(fileE);
        Files.createFile(fileF);
        Files.createFile(fileN);

        try {
            flattenDirectory(sourceDirectory);
            System.out.println("Directorio aplanado exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al aplanar el directorio: " + e.getMessage());
        }
    }

    public static void flattenDirectory(Path ruta) throws IOException {

        if (Files.exists(ruta)) {
           
            Files.walk(ruta)
                    .sorted(Collections.reverseOrder())
                    .forEach((Path a) -> {
                        if (Files.isRegularFile(a)) {
                            try {
                                Files.move(a.toAbsolutePath(), ruta.resolve(a.getFileName()));
                            } catch (IOException e) {
                                System.out.println("El archivo " + a + " no se ha podido mover");
                            }
                        } else if (Files.isDirectory(a) && !a.getFileName().equals(ruta.getFileName())) {
                            try {
                                Files.delete(a);
                            } catch (IOException f) {
                                System.out.println("No se ha podido eliminar la carpeta " + a);
                            }
                        }
                    });
        } else {
            throw new IOException("La ruta " + ruta + " no se encuentra");
        }
    }
}
