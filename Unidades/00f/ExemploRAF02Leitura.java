import java.io.*;

class ExemploRAF02Leitura {
   public static void main (String[] args) throws Exception{
      RandomAccessFile raf = new RandomAccessFile("exemploRAF.txt", "rw");

      int inteiro  = raf.readInt();
      double real = raf.readDouble();
      char caractere  = raf.readChar();
      boolean boleano  = raf.readBoolean();
      String str  = raf.readLine();

      raf.close();

      System.out.println("inteiro: " + inteiro);
      System.out.println("real: " + real);
      System.out.println("caractere: " + caractere);
      System.out.println("boleano: " + boleano);
      System.out.println("str: " + str);
   }
}

/* Resultados:

inteiro: 1
real: 5.3
caractere: X
boleano: true
str: Algoritmos

*/
