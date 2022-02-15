public class Hanoi{
    public static void hanoi(char src, char temp, char dest, int n){
        if(n == 1)System.out.printf("%d번째 고리가 %c에서 %c로 이동\n", n, src,dest);
        else{
            hanoi(src,dest,temp,n-1);
            System.out.printf("%d번째 고리가 %c에서 %c로 이동\n", n, src,dest);
            hanoi(temp,src,dest,n-1);
        }
    }
    public static void main(String [] args){
        hanoi('A','B','C',2);
    }
}