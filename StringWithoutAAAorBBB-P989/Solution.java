class Solution {
    public String strWithout3a3b(int a, int b) {
        StringBuffer sb = new StringBuffer();
        int mbig = a;
        char mbigc = 'a';
        int msmall = b;
        char msmallc = 'b';
        int blksize = 2;
        
        if (b > a) {
            mbig = b;
            mbigc = 'b';
            msmall = a;
            msmallc = 'a';
        }
               
        while (mbig != 0) {
            int bblk = mbig/2 + (mbig % 2);
            int sblk = msmall/2 + (msmall%2);
            blksize = 2;
            if (bblk - sblk >= 2) {
                blksize = 1;
            }

            if (mbig >= 2) {
                sb.append(mbigc);
                sb.append(mbigc);
                mbig-=2;
            } else {
                sb.append(mbigc);
                mbig-=1;
            }
            if (msmall >= 2) {
                for (int i = 0; i < blksize; i++, sb.append(msmallc));
                msmall-=blksize;
            } else if (msmall == 1) {
                sb.append(msmallc);
                msmall-=1;
            }
            
            if (msmall > mbig) {
                int tmp = mbig;
                mbig = msmall;
                msmall=mbig;
                char tmpc = mbigc;
                mbigc = msmallc;
                msmallc = tmpc;
            }
        }
        
        if (msmall != 0) {
            for (int i = 0; i< msmall; i++) {
                sb.append(msmallc);
            }
        }
        
        return sb.toString();
    }
}
