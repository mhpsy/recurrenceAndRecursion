package chooseDynamicProgramming;

import java.util.HashMap;

public class choose1 {
    public static void main(String[] args) {
        Invitation[] invitations = {
                new Invitation(1, 2),
                new Invitation(1, 4),
                new Invitation(2, 2),
                new Invitation(2, 6),
                new Invitation(3, 4),
                new Invitation(3, 5),
        };

        System.out.println(Invitation.choose(invitations, 5));
        System.out.println(Invitation.choose2(invitations, 5));
        System.out.println(Invitation.choose3(invitations, 5));
        System.out.println(Invitation.choose4(invitations, 5));

    }


}
