import Utils.Consts;

import static Utils.Consts.Application.*;

public class Application {
    public static boolean argsNotValid(String[] args) {
        return args.length < MIN_ARGS || args.length > MAX_ARGS;
    }

    public static void main(String[] args) {
        if (argsNotValid(args)) {
            System.out.println("Usage: java Application <peer_ap> <sub_protocol> <opnd_1> <opnd_2>");
            return;
        }
    }
}
