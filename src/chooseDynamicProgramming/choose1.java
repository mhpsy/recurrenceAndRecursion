package chooseDynamicProgramming;

import java.util.HashMap;
import java.util.Map;

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
        System.out.println(
                Invitation.choose5(invitations, 0, 5)
                        .values().stream()
                        .max(Integer::compareTo).orElse(-1)
        );

        System.out.println(Invitation.choose6(invitations, 5));
    }


}
