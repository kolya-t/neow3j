package io.neow3j.protocol.core.methods.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import io.neow3j.transaction.Cosigner;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {

    @JsonProperty("hash")
    private String transactionId;

    @JsonProperty("size")
    private Long size;

    @JsonProperty("version")
    private Integer version;

    @JsonProperty("nonce")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer nonce;

    @JsonProperty("sender")
    private String sender;

    @JsonProperty("sys_fee")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String sysFee;

    @JsonProperty("net_fee")
    private String netFee;

    @JsonProperty("valid_until_block")
    private Integer validUntilBlock;

    @JsonProperty("attributes")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private List<TransactionAttribute> attributes;

    @JsonProperty("cosigners")
    private List<Cosigner> cosigners;

    @JsonProperty("script")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String script;

    @JsonProperty("witnesses")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private List<Script> scripts;

    @JsonProperty("blockhash")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String blockHash;

    @JsonProperty("confirmations")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long confirmations;

    @JsonProperty("blocktime")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long blockTime;

    public Transaction() {
    }

    public Transaction(String transactionId, Long size, Integer version, Integer nonce,
            String sender, String sysFee, String netFee, Integer validUntilBlock,
            List<TransactionAttribute> attributes,
            List<Cosigner> cosigners, String script,
            List<Script> scripts, String blockHash, Long confirmations, Long blockTime) {
        this.transactionId = transactionId;
        this.size = size;
        this.version = version;
        this.nonce = nonce;
        this.sender = sender;
        this.sysFee = sysFee;
        this.netFee = netFee;
        this.validUntilBlock = validUntilBlock;
        this.attributes = attributes;
        this.cosigners = cosigners;
        this.script = script;
        this.scripts = scripts;
        this.blockHash = blockHash;
        this.confirmations = confirmations;
        this.blockTime = blockTime;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Long getSize() {
        return size;
    }

    public Integer getVersion() {
        return version;
    }

    public Integer getNonce() {
        return nonce;
    }

    public String getSender() {
        return sender;
    }

    public String getSysFee() {
        return sysFee;
    }

    public String getNetFee() {
        return netFee;
    }

    public Integer getValidUntilBlock() {
        return validUntilBlock;
    }

    public List<TransactionAttribute> getAttributes() {
        return attributes;
    }

    public List<Cosigner> getCosigners() {
        return cosigners;
    }

    public String getScript() {
        return script;
    }

    public List<Script> getScripts() {
        return scripts;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public Long getConfirmations() {
        return confirmations;
    }

    public Long getBlockTime() {
        return blockTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Transaction that = (Transaction) o;
        return Objects.equals(transactionId, that.transactionId) &&
                Objects.equals(size, that.size) &&
                Objects.equals(version, that.version) &&
                Objects.equals(nonce, that.nonce) &&
                Objects.equals(sender, that.sender) &&
                Objects.equals(sysFee, that.sysFee) &&
                Objects.equals(netFee, that.netFee) &&
                Objects.equals(validUntilBlock, that.validUntilBlock) &&
                Objects.equals(attributes, that.attributes) &&
                Objects.equals(cosigners, that.cosigners) &&
                Objects.equals(script, that.script) &&
                Objects.equals(scripts, that.scripts) &&
                Objects.equals(blockHash, that.blockHash) &&
                Objects.equals(confirmations, that.confirmations) &&
                Objects.equals(blockTime, that.blockTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, size, version, nonce, sender, sysFee, netFee,
                validUntilBlock, attributes, cosigners, script, scripts, blockHash, confirmations,
                blockTime);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", size=" + size +
                ", version=" + version +
                ", nonce=" + nonce +
                ", sender='" + sender + '\'' +
                ", systemFee='" + sysFee + '\'' +
                ", networkFee='" + netFee + '\'' +
                ", validUntilBlock=" + validUntilBlock +
                ", attributes=" + attributes +
                ", cosigners=" + cosigners +
                ", script='" + script + '\'' +
                ", scripts=" + scripts +
                ", blockHash='" + blockHash + '\'' +
                ", confirmations=" + confirmations +
                ", blockTime=" + blockTime +
                '}';
    }
}
