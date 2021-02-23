package recursion;

public class Amount {

    public static void main(String[] args) {
        Amount amount = new Amount();
        System.out.println(amount.change2(5,new int[]{1,2,5}));
    }

    public int change2(int amount, int[] coins) {
        int[] dp = new int[amount+1];
        dp[0] = 1;
        for (int j = 1; j <= amount; j++){ //枚举金额
            for (int coin : coins){ //枚举硬币
                if (j < coin) continue; // coin不能大于amount
                dp[j] += dp[j-coin];
            }
        }
        return dp[amount];
    }


    int count = 0;
    public int change(int amount, int[] coins) {
        if (coins.length <= 0 ){
            return 0;
        }
        find(amount,coins,0);
        return count;
    }

    private void find(int amount,int[] coins,int sum){
        System.out.println("sum = "+sum);
        if (sum > amount){
            System.out.println("*****************notfind");
            return ;
        }else if(sum == amount){
            System.out.println("---------------find");
            count++;
            return;
        }

        for(int i = 0; i < coins.length ; i++){
            System.out.println("i = "+i+" start: sum:"+sum+" i:"+coins[i]);
            find(amount,coins,sum+coins[i]);
            System.out.println("i = "+i+" startend: sum:"+sum+" i:"+coins[i]);
        }
    }
}
