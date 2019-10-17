package io.neow3j.transaction;

import io.neow3j.constants.NeoConstants;
import io.neow3j.contract.ScriptHash;
import io.neow3j.utils.Numeric;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TransactionTest {

    @Test
    public void buildTransaction() {
        Transaction t = new Transaction.Builder()
                .build();

        assertThat(t.getVersion(), is(NeoConstants.CURRENT_TX_VERSION));
    }

    @Test
    public void addWitnessesInBuilderAndCheckOrdering() {
        // first message has script hash 159759880646822985762674987218710759559479736571 (as integer)
        byte[] m1 = Numeric.hexStringToByteArray("01a402d8");
        // first message has script hash 776468865644545852461964229176363821261390671687 (as integer)
        byte[] m2 = Numeric.hexStringToByteArray("d802a401");
        // first message has script hash 226912894221247444770625744046962264064050576762 (as integer)
        byte[] m3 = Numeric.hexStringToByteArray("a7b3a191");
        Witness s1 = new Witness(m1, ScriptHash.fromScript(m1));
        Witness s2 = new Witness(m2, ScriptHash.fromScript(m2));
        Witness s3 = new Witness(m3, ScriptHash.fromScript(m3));

        Transaction tx = new Transaction.Builder()
                .witness(s1)
                .witness(s2)
                .witness(s3)
                .build();
        assertEquals(tx.getWitnesses().get(0).getScriptHash(), s1.getScriptHash());
        assertEquals(tx.getWitnesses().get(1).getScriptHash(), s3.getScriptHash());
        assertEquals(tx.getWitnesses().get(2).getScriptHash(), s2.getScriptHash());

        // Add in reverse order
        tx = new Transaction.Builder()
                .witness(s3)
                .witness(s2)
                .witness(s1)
                .build();
        assertEquals(tx.getWitnesses().get(0).getScriptHash(), s1.getScriptHash());
        assertEquals(tx.getWitnesses().get(1).getScriptHash(), s3.getScriptHash());
        assertEquals(tx.getWitnesses().get(2).getScriptHash(), s2.getScriptHash());

        // Add all together
        tx = new Transaction.Builder()
                .witnesses(Arrays.asList(s3, s1, s2))
                .build();
        assertEquals(tx.getWitnesses().get(0).getScriptHash(), s1.getScriptHash());
        assertEquals(tx.getWitnesses().get(1).getScriptHash(), s3.getScriptHash());
        assertEquals(tx.getWitnesses().get(2).getScriptHash(), s2.getScriptHash());
    }

    @Test
    public void addWitnessesInTxAndCheckOrdering() {
        // first message has script hash 159759880646822985762674987218710759559479736571 (as integer)
        byte[] m1 = Numeric.hexStringToByteArray("01a402d8");
        // first message has script hash 776468865644545852461964229176363821261390671687 (as integer)
        byte[] m2 = Numeric.hexStringToByteArray("d802a401");
        // first message has script hash 226912894221247444770625744046962264064050576762 (as integer)
        byte[] m3 = Numeric.hexStringToByteArray("a7b3a191");
        Witness s1 = new Witness(m1, ScriptHash.fromScript(m1));
        Witness s2 = new Witness(m2, ScriptHash.fromScript(m2));
        Witness s3 = new Witness(m3, ScriptHash.fromScript(m3));

        Transaction tx = new Transaction.Builder()
                .witness(s1).build();
        tx.addWitness(s2);
        tx.addWitness(s3);
        assertEquals(tx.getWitnesses().get(0).getScriptHash(), s1.getScriptHash());
        assertEquals(tx.getWitnesses().get(1).getScriptHash(), s3.getScriptHash());
        assertEquals(tx.getWitnesses().get(2).getScriptHash(), s2.getScriptHash());

        // Add in different order
        tx = new Transaction.Builder().build();
        tx.addWitness(s2);
        tx.addWitness(s1);
        tx.addWitness(s3);
        assertEquals(tx.getWitnesses().get(0).getScriptHash(), s1.getScriptHash());
        assertEquals(tx.getWitnesses().get(1).getScriptHash(), s3.getScriptHash());
        assertEquals(tx.getWitnesses().get(2).getScriptHash(), s2.getScriptHash());
    }

}