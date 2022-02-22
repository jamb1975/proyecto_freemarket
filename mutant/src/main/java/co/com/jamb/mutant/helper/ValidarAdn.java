package co.com.jamb.mutant.helper;

public class ValidarAdn {

    public static boolean validarAdn(String[] adn){
        String [] letras = {"A", "T", "C" ,"G"};

        for(String s :adn){
          for(int j = 0; j < s.length(); j++){
              int countLetras = 0;
              for(int k = 0; k < letras.length; k++){
                  if(s.charAt(j) == letras[k].charAt(0)){ countLetras++;}
              }
              if(countLetras == 0) { return false;}
           }

        }
    return true;
    }
}
