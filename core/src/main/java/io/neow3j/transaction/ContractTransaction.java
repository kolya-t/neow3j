package io.neow3j.transaction;

import java.util.List;

public class ContractTransaction {

    public byte[] toArray() {
        return new byte[0];
    }

    public byte[] toArrayWithoutScripts() {
        return new byte[0];
    }

    public void addScript(Witness witness) {

    }

    public static class Builder {
        public Builder outputs(List<TransactionOutput> outputs) {
            return this;
        }

        public Builder inputs(List<TransactionInput> inputs) {
            return this;
        }

        public Builder scripts(List<Witness> witnesses) {
            return this;
        }

        public Builder attributes(List<TransactionAttribute> attributes) {
            return this;
        }

        public ContractTransaction build() {
            return null;
        }
    }
}
