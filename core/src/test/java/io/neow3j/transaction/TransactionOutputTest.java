package io.neow3j.transaction;

import io.neow3j.model.types.NEOAsset;
import org.junit.Test;

import static org.junit.Assert.*;

public class TransactionOutputTest {

    @Test
    public void testEqualsWithDifferentDecimalNotation() {
        TransactionOutput o1 = new TransactionOutput(NEOAsset.HASH_ID, "15983.0", "address");
        TransactionOutput o2 = new TransactionOutput(NEOAsset.HASH_ID, "15983", "address");
        assertEquals(o1, o2);
    }

}