class ExemploArq03Exercicio {
   public static void main(String[] args) {
      Arq.openRead("exemplo.txt");

      /*
      String str = "";
      while (Arq.hasNext() == true) {
         str += Arq.readChar();
      }
      */

      // outra forma => funciona melhor
      String str = Arq.readAll();

      Arq.close();

      Arq.openWrite("copia.txt");
      Arq.print(str);
      Arq.close();
   }
}
