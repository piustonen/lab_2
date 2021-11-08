package com.company;
import java.util.Scanner;
import java.util.Arrays;
import java.lang.Exception;
import java.util.regex.Pattern;



public class Calculator {
    public static void main(String[] args) throws Exception {
        String input;
        Scanner in = new Scanner(System.in);
        System.out.print("Input your string:");
        input = in.nextLine(); //in обьект сосканировал строчку
        System.out.println("Result:"+Add(input));
        System.out.println("Result:"+Add_float(input));
    }

    public static int Add(String numbers) throws Exception {
        char[] symb;
        int last = 0;
        symb = numbers.toCharArray();
        String[] delimiters = new String[0];
        if (symb.length == 0) return 0;
        if (symb[0] != '/') {//первый ли символ слэш?-> if not - не меняется delimiter.... варианты ввода: или не меняется разделитель, или один или несколько
            delimiters = Arrays.copyOf(delimiters, delimiters.length+2);//массив делиметрив оголошуєм, длинной 2. присвоила его копию, но длина на 2 увеличила
            delimiters[0] = ","; //отдаем кому и слєш єн как разделитель
            delimiters[1] = "\\n";
            return Word_delim_work(numbers, delimiters);// numbers - стринг элемент, с которым мы работаем, делиметр - массив делиметров
        }
        if (symb[1] == '/') { //если идет слєш 2 раза(и сымб0 и сымб 0)
            if (symb[2]!='[') { //один разделитель
                delimiters = Arrays.copyOf(delimiters, delimiters.length+1);//в эррей пихаю делиметры
                delimiters[0] = Character.toString(symb[2]);//передаю разделит 2 знак
                return Word_delim_work(numbers.substring(5, symb.length), delimiters);//отрезаю ряд после 5 элемента, чтобы определить, какойу меня delimiter
            }
            else{//то есть несколько раздельников
                for (int i = 0; i<symb.length; i++){
                    if(symb[i]==']' & symb[i+1]!='[') {last=i; break;}} }//ищу закрытую дужкку
            delimiters = numbers.substring(3, last).replace("[", "").split("]"); //івв][вів вот эту штуку удаляю, выделяя делимитры, ищу закрытую, после которой нет открытой
            return Word_delim_work(numbers.substring(last+3), delimiters);// вырезаю ряд с 3
        }
        return 0;

    }

    public static class NegativeValueException extends Exception {
        public NegativeValueException(String errorMessage) {
            super(errorMessage);
        }
    }//доповнює експешены, якщо відємне число

    public static int Word_delim_work(String numbers, String[] delimiters) throws Exception {
        String[] clear_number = new String[1];
        clear_number[0]=numbers;
        int max;
        int index;
        for(String delim: delimiters){//каждый элемент делиметров
            String searchFor = Pattern.quote(delim);//используем, потому что оно может плохо делить по звездочкам(звездочка-спец символ) Pattern.qoute-возврат литерала строки
            max = clear_number.length;//чистим делиметры в  массиве со стрингом
            for(int i=0;i< max;i++ ){
                String[] buffer = clear_number[i].split(searchFor);//создаем массив для работы, деля по делиметру, SearchFor-delimiter
                int current_max = clear_number.length;
                clear_number = Arrays.copyOf(clear_number, current_max-1+buffer.length);//подаю элементы в изначальный массив
                clear_number[i]=buffer[0];
                index = 1;
                for(int j = current_max; j<current_max-1+buffer.length;j++){
                    clear_number[j] = buffer[index];
                    index = index + 1;
                }
            }
        }//сюда
        int[] str_num = new int[0];//прохожу рядки и делаю из них число
        for (String numb: clear_number){
            if(!numb.equals("")){
                str_num = Arrays.copyOf(str_num, str_num.length + 1);
                str_num[str_num.length - 1] = Integer.parseInt(numb);}//Прохожу каждый элемент нового массива и если элемент не пустой - добавляю его в новый массив чисел через ПасрИнт
        }
        int sum = 0;
        int[] negatives = new int[0];//если число отрицательное
        for (int check : str_num)
            if (check < 0) {
                negatives = Arrays.copyOf(negatives, negatives.length + 1);
                negatives[negatives.length - 1] = check;
            }//если в массиве негативных чисел больше 1 элемента->говорю, что негативные значения запрещены
        if (negatives.length > 0)
            throw new NegativeValueException("Negatives are not allowed:" + Arrays.toString(negatives));
        for (int toadd : str_num) if (toadd <= 1000) sum = sum + toadd;// если значения не больше 1000, то плюсую значения
        return sum;//выкинуть ошибку, если негативное значение
    }
    public static float Add_float(String numbers) throws Exception {
        char[] symb;
        int last = 0;
        symb = numbers.toCharArray();
        String[] delimiters = new String[0];
        if (symb.length == 0) return 0;
        if (symb[0] != '/') {
            delimiters = Arrays.copyOf(delimiters, delimiters.length+2);
            delimiters[0] = ",";
            delimiters[1] = "\\n";
            return Word_delim_work_float(numbers, delimiters);
        }
        if (symb[1] == '/') {
            if (symb[2]!='[') {
                delimiters = Arrays.copyOf(delimiters, delimiters.length+1);
                delimiters[0] = Character.toString(symb[2]);
                return Word_delim_work_float(numbers.substring(5, symb.length), delimiters);
            }
            else{
                for (int i = 0; i<symb.length; i++){
                    if(symb[i]==']' & symb[i+1]!='[') {last=i; break;}} }
            delimiters = numbers.substring(3, last).replace("[", "").split("]");
            return Word_delim_work_float(numbers.substring(last+3), delimiters);
        }
        return 0;

    }

    public static float Word_delim_work_float(String numbers, String[] delimiters) throws Exception {
        String[] clear_number = new String[1];
        clear_number[0]=numbers;
        int max;
        int index;
        for(String delim: delimiters){
            String searchFor = Pattern.quote(delim);
            max = clear_number.length;
            for(int i=0;i< max;i++ ){
                String[] buffer = clear_number[i].split(searchFor);
                int current_max = clear_number.length;
                clear_number = Arrays.copyOf(clear_number, current_max-1+buffer.length);
                clear_number[i]=buffer[0];
                index = 1;
                for(int j = current_max; j<current_max-1+buffer.length;j++){
                    clear_number[j] = buffer[index];
                    index = index + 1;
                }
            }
        }
        float[] str_num = new float[0];
        //System.out.println(Arrays.toString(clear_number));
        for (String numb: clear_number){
            if(!numb.equals("")){
                str_num = Arrays.copyOf(str_num, str_num.length + 1);
                str_num[str_num.length - 1] = Float.parseFloat(numb);}
        }
        float sum = 0;
        float[] negatives = new float[0];
        for (float check : str_num)
            if (check < 0) {
                negatives = Arrays.copyOf(negatives, negatives.length + 1);
                negatives[negatives.length - 1] = check;
            }
        if (negatives.length > 0)
            throw new NegativeValueException("Negatives are not allowed:" + Arrays.toString(negatives));
        for (float toadd : str_num) if (toadd <= 1000) sum = sum + toadd;
        return sum;
    }
}