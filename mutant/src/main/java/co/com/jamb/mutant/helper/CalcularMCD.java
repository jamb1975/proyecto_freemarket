package co.com.jamb.mutant.helper;

public class CalcularMCD {

    public static Long calcular(Long num1, Long num2){
        try {
            Long cociente = Long.valueOf(0);
            Long residuo = Long.valueOf(0);
            Long acum = Long.valueOf(0);
            cociente = num1 > num2 ? num1 / num2 : num2 / num1;
            residuo = num1 > num2 ? num1 % num2 : num2 % num1;
            cociente = num1 < num2 ? num1 : num2;
            while (!residuo.equals(0)) {
                System.out.println("cociente=" + cociente + "Residuo=" + residuo);
                acum = cociente % residuo;
                cociente = residuo;

                if (acum == 0) {
                    return residuo;
                }
                residuo = acum;
            }
            return residuo;
        }catch(ArithmeticException a){
            return Long.valueOf(1);
        }
    }
}
