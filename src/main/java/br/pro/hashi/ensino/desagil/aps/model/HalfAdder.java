package br.pro.hashi.ensino.desagil.aps.model;

public class HalfAdder extends Gate {
    private final NandGate nandLeftAnd;
    private final NandGate nandRightAnd;

    private final NandGate nandLeftXor;
    private final NandGate nandTopXor;
    private final NandGate nandBottomXor;
    private final NandGate nandRightXor;

    public HalfAdder(){
        super("Half-Adder",2,2);

        nandLeftAnd = new NandGate();

        nandRightAnd = new NandGate();
        nandRightAnd.connect(0, nandLeftAnd);
        nandRightAnd.connect(1, nandLeftAnd);

        nandLeftXor = new NandGate();

        nandTopXor = new NandGate();
        nandTopXor.connect(1, nandLeftXor);

        nandBottomXor = new NandGate();
        nandBottomXor.connect(0, nandLeftXor);

        nandRightXor = new NandGate();
        nandRightXor.connect(0, nandTopXor);
        nandRightXor.connect(1, nandBottomXor);
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            case 0:
                nandTopXor.connect(0, emitter);
                nandLeftXor.connect(0, emitter);
                break;
            case 1:
                nandLeftXor.connect(1, emitter);
                nandBottomXor.connect(1, emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputPin);
        }
        nandLeftAnd.connect(inputPin, emitter);
    }

    @Override
    public boolean read(int outputPin) {
        if (outputPin == 0) {
            return nandRightXor.read();
        } else if (outputPin == 1) {
            return nandRightAnd.read();
        } else {
            throw new IndexOutOfBoundsException(outputPin);
        }

    }
}
