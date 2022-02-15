import java.util.Stack;

public class PostfixEx {
    //in-coming-precedence (,),*,/,%,+,-,eos
    private int [] icp = {20,19,14,14,14,11,11,0};
    //in-stack-precedence 
    private int [] isp = {0,19,14,14,14,11,11,0};
    private Stack<Float> fStack; //후위연산을 계산할 때 사용
    private Stack<String> sStack; //중위연산을 후위연산으로 변환할 때 사용
    private String infix;
    private String postfix;
    public PostfixEx(){
        fStack=new Stack<Float>();
        sStack=new Stack<String>();
    }
    //연산자의 우선순위 비교를 위해 사용
    private int getPrecedence(String token){
        switch(token){
            case "(": return 0;
            case ")": return 1;
            case "*": return 2;
            case "/": return 3; 
            case "%": return 4;
            case "+": return 5;
            case "-": return 6;
            case "eos":return 7;
            default: return -1;
        }
    }
    //후위연산으로 변환하는 메서드
    private void toPostfix(){
        sStack.push("eos");
        postfix ="";
        String [] s = infix.split(" ");
        for(int i = 0; i<s.length;i++){
            switch(s[i]){
                case "(": //icp가 가장 높기 때문에 무조건 스택에 들어가고 이후 icp 는 가장 낮다.
                    sStack.push("("); break; 
                case ")":
                    while(!sStack.peek().equals("(")) postfix = postfix.concat(sStack.pop()+" ");
                    sStack.pop(); break;
                case "*":
                case "/":
                case "%":
                case "+":
                case "-":
                    while(isp[getPrecedence(sStack.peek())] >= icp[getPrecedence(s[i])]) postfix = postfix.concat(sStack.pop()+" ");
                    sStack.push(s[i]); break;
                default: //number
                    postfix = postfix.concat(s[i]+" ");
            }
        }
        while(!sStack.peek().equals("eos"))postfix = postfix.concat(sStack.pop()+" ");
        sStack.pop();
        System.out.println("postfix: "+postfix);
    }
    //후위식 연산
    private float postfixOp(){
        String [] s = postfix.split(" ");
        float op1, op2; //팝 팝 연산 푸쉬!
        for(int i = 0;i<s.length;i++){
            switch(s[i]){
                case "*":
                    op2 = fStack.pop(); op1 = fStack.pop(); fStack.push(op1*op2);
                    break;
                case "/":
                    op2 = fStack.pop(); op1 = fStack.pop(); fStack.push(op1/op2);
                    break;
                case "%":
                    op2 = fStack.pop(); op1 = fStack.pop(); fStack.push(op1%op2);
                    break;
                case "+":
                    op2 = fStack.pop(); op1 = fStack.pop(); fStack.push(op1+op2);
                    break;
                case "-":
                    op2 = fStack.pop(); op1 = fStack.pop(); fStack.push(op1-op2);
                    break;
                default: fStack.push(Float.parseFloat(s[i]));
            }
        }
        return fStack.pop();
    }
    public float calc(String infix){
        String tmp = "";
        char c;
        for(int i = 0;i<infix.length();i++){ //연산자와 피연산자의 구분을 위해 공백을 추가시킨다. 
            c=infix.charAt(i);
            if(c == '(') tmp=tmp.concat(c+" "); // 연산자 ( 숫자
            else if(c == ')')tmp=tmp.concat(" "+c);//숫자 ) 연산자
            else if(c < '0' || c >'9') tmp = tmp.concat(" "+c+" ");
            else tmp = tmp.concat(Character.toString(c));
        }
        this.infix=tmp;
        System.out.println("infix: "+this.infix);
        toPostfix();
        return postfixOp();
    }

    public static void main(String [] args){
        PostfixEx my = new PostfixEx();
        float f1 = (float)my.calc("1*2+3");
        System.out.println(f1);
        float f2 = (float)my.calc("3*(2+3)");
        System.out.println(f2);
        float f3 = (float)my.calc("(12-2)/2*4");
        System.out.println(f3);
        float f4 = (float)my.calc("123*43%2+123*(123+12)");
        System.out.println(f4);
        
    }
}
