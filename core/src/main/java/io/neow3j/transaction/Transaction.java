package io.neow3j.transaction;

import io.neow3j.constants.NeoConstants;
import io.neow3j.contract.ScriptHash;
import io.neow3j.crypto.Hash;
import io.neow3j.io.BinaryReader;
import io.neow3j.io.BinaryWriter;
import io.neow3j.io.NeoSerializable;
import io.neow3j.utils.ArrayUtils;
import io.neow3j.utils.Numeric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Transaction extends NeoSerializable {

    private static final Logger LOG = LoggerFactory.getLogger(Transaction.class);

    private byte version;
    private Integer nonce;
    private Integer validUntilBlock;
    private ScriptHash sender;
    private Long systemFee;
    private Long networkFee;
    private List<TransactionAttribute> attributes;
    private List<Cosigner> cosigners;
    private byte[] script;
    private List<Witness> witnesses;

    public Transaction() {
    }

    protected Transaction(Builder builder) {
        this.version = builder.version;
        this.nonce = builder.nonce;
        this.validUntilBlock = builder.validUntilBlock;
        this.sender = builder.sender;
        this.systemFee = builder.systemFee;
        this.networkFee = builder.networkFee;
        this.attributes = builder.attributes;
        this.cosigners = builder.cosigners;
        this.script = builder.script;
        this.witnesses = builder.witnesses;
    }

    public byte getVersion() {
        return version;
    }

    public Integer getNonce() {
        return nonce;
    }

    public Integer getValidUntilBlock() {
        return validUntilBlock;
    }

    public ScriptHash getSender() {
        return sender;
    }

    public Long getSystemFee() {
        return systemFee;
    }

    public Long getNetworkFee() {
        return networkFee;
    }

    public List<TransactionAttribute> getAttributes() {
        return attributes;
    }

    public List<Cosigner> getCosigners() {
        return cosigners;
    }

    public byte[] getScript() {
        return script;
    }

    public List<Witness> getWitnesses() {
        return witnesses;
    }

    /**
     * Adds the given invocation script (e.g. signatures) and the verification script to this
     * transaction's list of witnesses.
     *
     * @param invocationScript   The invocation script of the witness.
     * @param verificationScript The verification script of the witness.
     */
    public void addWitness(InvocationScript invocationScript, VerificationScript verificationScript) {
        addWitness(new Witness(invocationScript, verificationScript));
    }

    public void addWitness(Witness witness) {
        if (witness.getScriptHash() == null || witness.getScriptHash().length() == 0) {
            throw new IllegalArgumentException("The script hash of the given script is " +
                    "empty. Please set the script hash.");
        }
        this.witnesses.add(witness);
        this.witnesses.sort(Comparator.comparing(Witness::getScriptHash));
    }

    public String getTxId() {
        byte[] hash = Hash.sha256(Hash.sha256(toArrayWithoutScripts()));
        return Numeric.toHexStringNoPrefix(ArrayUtils.reverseArray(hash));
    }

    public int getSize() {
        // TODO 2019-08-05 claude:
        // Implement more efficiently, e.g. with fixed byte values and calls to the getSize() of
        // the transaction components.
        return toArray().length;
    }

    @Override
    public void deserialize(BinaryReader reader) throws IOException {
        this.version = reader.readByte();
        try {
            this.attributes = reader.readSerializableList(TransactionAttribute.class);
            this.witnesses = reader.readSerializableList(Witness.class);
        } catch (IllegalAccessException e) {
            LOG.error("Can't access the specified object.", e);
        } catch (InstantiationException e) {
            LOG.error("Can't instantiate the specified object type.", e);
        }
    }

    private void serializeWithoutScripts(BinaryWriter writer) throws IOException {
        writer.writeByte(this.version);
        writer.writeSerializableVariable(this.attributes);
    }

    @Override
    public void serialize(BinaryWriter writer) throws IOException {
        serializeWithoutScripts(writer);
        writer.writeSerializableVariable(this.witnesses);
    }

    /**
     * Serializes this transaction to a raw byte array without any scripts. This is required if the
     * serialized transaction gets signed, e.g. by an external keypair/provider.
     *
     * @return the serialized transaction
     */
    public byte[] toArrayWithoutScripts() {
        try (ByteArrayOutputStream ms = new ByteArrayOutputStream()) {
            try (BinaryWriter writer = new BinaryWriter(ms)) {
                serializeWithoutScripts(writer);
                writer.flush();
                return ms.toByteArray();
            }
        } catch (IOException ex) {
            throw new UnsupportedOperationException(ex);
        }
    }

    /**
     * Serializes this transaction to a raw byte array including scripts (witnesses/signatures).
     * The byte array can be sent as a transaction with the `sendrawtransaction` RPC method.
     *
     * @return the serialized transaction.
     */
    @Override
    public byte[] toArray() {
        return super.toArray();
    }

    protected static class Builder {

        public Integer nonce;
        private byte version;
        public Integer validUntilBlock;
        public ScriptHash sender;
        public Long systemFee;
        public Long networkFee;
        public List<Cosigner> cosigners;
        public byte[] script;
        private List<TransactionAttribute> attributes;
        private List<Witness> witnesses;

        protected Builder() {
            this.nonce =
            this.version = NeoConstants.CURRENT_TX_VERSION;
            this.attributes = new ArrayList<>();
            this.witnesses = new ArrayList<>();
        }

        public Builder version(byte version) {
            this.version = version;
            return this;
        }

        public Builder attributes(List<TransactionAttribute> attributes) {
            this.attributes.addAll(attributes);
            return this;
        }

        public Builder attribute(TransactionAttribute attribute) {
            return attributes(Arrays.asList(attribute));
        }

        public Builder witnesses(List<Witness> witnesses) {
            for (Witness witness : witnesses) {
                if (witness.getScriptHash() == null || witness.getScriptHash().length() == 0) {
                    throw new IllegalArgumentException("The script hash of the given script is " +
                            "empty. Please set the script hash.");
                }
            }

            this.witnesses.addAll(witnesses);
            this.witnesses.sort(Comparator.comparing(Witness::getScriptHash));
            return this;
        }

        public Builder witness(Witness witness) {
            return witnesses(Arrays.asList(witness));
        }

        public Transaction build() {
            return new Transaction(this);
        }
    }
}
