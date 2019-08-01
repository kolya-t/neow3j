package io.neow3j.contract;

import io.neow3j.io.NeoSerializableInterface;
import io.neow3j.model.types.ContractParameterType;
import io.neow3j.utils.Numeric;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ContractDeploymentScriptTest {

    private byte[] contract;

    @Before
    public void setUp() throws IOException {
        InputStream contractAsStream = this.getClass().getResourceAsStream("/contracts/ico-test1.avm");
        this.contract = IOUtils.toByteArray(contractAsStream);
    }

    @Test
    public void serialize() {

        ContractDeploymentScript cs = new ContractDeploymentScript(
                this.contract,
                new ContractFunctionProperties(
                        Arrays.asList(
                                ContractParameterType.STRING,
                                ContractParameterType.ARRAY
                        ),
                        ContractParameterType.BYTE_ARRAY,
                        true,
                        true,
                        true
                ),
                new ContractDescriptionProperties(
                        "Test",
                        "0.1",
                        "Guil",
                        "email@email.com",
                        "Anything."
                )
        );

        assertThat(
                Numeric.toHexStringNoPrefix(cs.toArray()),
                is("09416e797468696e672e0f656d61696c40656d61696c2e636f6d044775696c03302e31045465737457550207104dd10a56c56b6c766b00527ac46c766b51527ac46168164e656f2e52756e74696d652e47657454726967676572639800611423ba2703c53263e8d6e522dc32203339dcd8eee9c00114907c907c9e633900611423ba2703c53263e8d6e522dc32203339dcd8eee96168184e656f2e52756e74696d652e436865636b5769746e657373616c7566611423ba2703c53263e8d6e522dc32203339dcd8eee9c00121907c907c9e6343016a00c3611423ba2703c53263e8d6e522dc32203339dcd8eee9ac616c75666168164e656f2e52756e74696d652e4765745472696767657260907c907c9e6303016a00c3066465706c6f7987640b0061657a01616c75666a00c30a6d696e74546f6b656e7387640b0061657702616c75666a00c30b746f74616c537570706c7987640b006165ed03616c75666a00c3046e616d6587640b006165f800616c75666a00c30673796d626f6c87640b006165fb00616c75666a00c3087472616e7366657287643a006a51c3c0539c63080000616c75666a51c300c36a51c351c36a54527ac46a51c352c36a55527ac46a54c36a55c361527265bb03616c75666a00c30962616c616e63654f6687641e006a51c3c0519c63080000616c75666a51c300c361655105616c75666a00c308646563696d616c7387640b0061658800616c75666165b2066a52527ac46165ca076a53527ac46a53c300907c907ca16330006a52c3c0642900616a52c36a53c3617c06726566756e6453c168124e656f2e52756e74696d652e4e6f7469667900616c756600c56b116e616d65206f662074686520746f6b656e616c756600c56b1053796d626f6c4f66546865546f6b656e616c756600c56b58616c756600c56b6168164e656f2e53746f726167652e476574436f6e746578740b746f74616c537570706c79617c680f4e656f2e53746f726167652e476574c064080000616c75666168164e656f2e53746f726167652e476574436f6e74657874611423ba2703c53263e8d6e522dc32203339dcd8eee907008053ee7ba80a615272680f4e656f2e53746f726167652e5075746168164e656f2e53746f726167652e476574436f6e746578740b746f74616c537570706c7907008053ee7ba80a615272680f4e656f2e53746f726167652e5075746100611423ba2703c53263e8d6e522dc32203339dcd8eee907008053ee7ba80a615272087472616e7366657254c168124e656f2e52756e74696d652e4e6f7469667951616c756656c56b61650f056a00527ac46a00c3c063080000616c756661651b066a51527ac46165ad036a52527ac46a52c3632e00616a00c36a51c3617c06726566756e6453c168124e656f2e52756e74696d652e4e6f7469667900616c75666a00c36a51c36a52c361527265b4036a53527ac46a53c363080000616c75666168164e656f2e53746f726167652e476574436f6e746578746a00c3617c680f4e656f2e53746f726167652e4765746a54527ac46168164e656f2e53746f726167652e476574436f6e746578746a00c36a53c36a54c393615272680f4e656f2e53746f726167652e5075746168164e656f2e53746f726167652e476574436f6e746578740b746f74616c537570706c79617c680f4e656f2e53746f726167652e4765746a55527ac46168164e656f2e53746f726167652e476574436f6e746578740b746f74616c537570706c796a53c36a55c393615272680f4e656f2e53746f726167652e50757461006a00c36a53c3615272087472616e7366657254c168124e656f2e52756e74696d652e4e6f7469667951616c756600c56b6168164e656f2e53746f726167652e476574436f6e746578740b746f74616c537570706c79617c680f4e656f2e53746f726167652e476574616c756655c56b6c766b00527ac46c766b51527ac46c766b52527ac46a52c300a164080000616c75666a00c36168184e656f2e52756e74696d652e436865636b5769746e65737363080000616c75666a51c3c001149c63080000616c75666168164e656f2e53746f726167652e476574436f6e746578746a00c3617c680f4e656f2e53746f726167652e4765746a53527ac46a53c36a52c39f64080000616c75666a00c36a51c3907c907c9e63080051616c75666a53c36a52c39c6438006168164e656f2e53746f726167652e476574436f6e746578746a00c3617c68124e656f2e53746f726167652e44656c657465623a006168164e656f2e53746f726167652e476574436f6e746578746a00c36a53c36a52c394615272680f4e656f2e53746f726167652e5075746168164e656f2e53746f726167652e476574436f6e746578746a51c3617c680f4e656f2e53746f726167652e4765746a54527ac46168164e656f2e53746f726167652e476574436f6e746578746a51c36a54c36a52c393615272680f4e656f2e53746f726167652e507574616a00c36a51c36a52c3615272087472616e7366657254c168124e656f2e52756e74696d652e4e6f7469667951616c756651c56b6c766b00527ac46168164e656f2e53746f726167652e476574436f6e746578746a00c3617c680f4e656f2e53746f726167652e476574616c756651c56b6168134e656f2e52756e74696d652e47657454696d650480bfcf59946a00527ac46a00c300a263080000616c75666a00c3048033e101a2630d000500e8764817616c756600616c756656c56b6c766b00527ac46c766b51527ac46c766b52527ac46a51c30400e1f505966a52c3956a53527ac46168164e656f2e53746f726167652e476574436f6e746578740b746f74616c537570706c79617c680f4e656f2e53746f726167652e4765746a54527ac4070000c16ff286236a54c3946a55527ac46a55c300a1642e00616a00c36a51c3617c06726566756e6453c168124e656f2e52756e74696d652e4e6f7469667900616c75666a55c36a53c39f643f00616a00c36a53c36a55c3946a52c3960400e1f50595617c06726566756e6453c168124e656f2e52756e74696d652e4e6f746966796a55c36a53527ac46a53c3616c756653c56b61682953797374656d2e457865637574696f6e456e67696e652e476574536372697074436f6e7461696e657261681d4e656f2e5472616e73616374696f6e2e4765745265666572656e6365736a00527ac4006a51527ac46280006a00c36a51c3c36a52527ac46a52c36168154e656f2e4f75747075742e4765744173736574496461209b7cffdaa674beae0f930ebe6085af9093e5fe56b34a5c220ccdcf6efc336fc5907c907c9e6325006a52c36168184e656f2e4f75747075742e47657453637269707448617368616c75666a51c351936a51527ac46a51c36a00c3c09f637bff00616c756600c56b61682d53797374656d2e457865637574696f6e456e67696e652e476574457865637574696e6753637269707448617368616c756654c56b61682953797374656d2e457865637574696f6e456e67696e652e476574536372697074436f6e7461696e657261681a4e656f2e5472616e73616374696f6e2e4765744f757470757473006a00527ac46a51527ac4006a52527ac462aa006a51c36a52c3c36a53527ac46a53c36168184e656f2e4f75747075742e4765745363726970744861736861653eff907c907c9e636a006a53c36168154e656f2e4f75747075742e4765744173736574496461209b7cffdaa674beae0f930ebe6085af9093e5fe56b34a5c220ccdcf6efc336fc5907c907c9e6325006a00c36a53c36168134e656f2e4f75747075742e47657456616c7565936a00527ac46a52c351936a52527ac46a52c36a51c3c09f6351ff6a00c3616c756668134e656f2e436f6e74726163742e437265617465")
        );

    }

    @Test
    public void deserialize() throws IllegalAccessException, InstantiationException {

        String contractDeploymentScript = "09416e797468696e672e0f656d61696c40656d61696c2e636f6d044775696c03302e31045465737457550207104dd10a56c56b6c766b00527ac46c766b51527ac46168164e656f2e52756e74696d652e47657454726967676572639800611423ba2703c53263e8d6e522dc32203339dcd8eee9c00114907c907c9e633900611423ba2703c53263e8d6e522dc32203339dcd8eee96168184e656f2e52756e74696d652e436865636b5769746e657373616c7566611423ba2703c53263e8d6e522dc32203339dcd8eee9c00121907c907c9e6343016a00c3611423ba2703c53263e8d6e522dc32203339dcd8eee9ac616c75666168164e656f2e52756e74696d652e4765745472696767657260907c907c9e6303016a00c3066465706c6f7987640b0061657a01616c75666a00c30a6d696e74546f6b656e7387640b0061657702616c75666a00c30b746f74616c537570706c7987640b006165ed03616c75666a00c3046e616d6587640b006165f800616c75666a00c30673796d626f6c87640b006165fb00616c75666a00c3087472616e7366657287643a006a51c3c0539c63080000616c75666a51c300c36a51c351c36a54527ac46a51c352c36a55527ac46a54c36a55c361527265bb03616c75666a00c30962616c616e63654f6687641e006a51c3c0519c63080000616c75666a51c300c361655105616c75666a00c308646563696d616c7387640b0061658800616c75666165b2066a52527ac46165ca076a53527ac46a53c300907c907ca16330006a52c3c0642900616a52c36a53c3617c06726566756e6453c168124e656f2e52756e74696d652e4e6f7469667900616c756600c56b116e616d65206f662074686520746f6b656e616c756600c56b1053796d626f6c4f66546865546f6b656e616c756600c56b58616c756600c56b6168164e656f2e53746f726167652e476574436f6e746578740b746f74616c537570706c79617c680f4e656f2e53746f726167652e476574c064080000616c75666168164e656f2e53746f726167652e476574436f6e74657874611423ba2703c53263e8d6e522dc32203339dcd8eee907008053ee7ba80a615272680f4e656f2e53746f726167652e5075746168164e656f2e53746f726167652e476574436f6e746578740b746f74616c537570706c7907008053ee7ba80a615272680f4e656f2e53746f726167652e5075746100611423ba2703c53263e8d6e522dc32203339dcd8eee907008053ee7ba80a615272087472616e7366657254c168124e656f2e52756e74696d652e4e6f7469667951616c756656c56b61650f056a00527ac46a00c3c063080000616c756661651b066a51527ac46165ad036a52527ac46a52c3632e00616a00c36a51c3617c06726566756e6453c168124e656f2e52756e74696d652e4e6f7469667900616c75666a00c36a51c36a52c361527265b4036a53527ac46a53c363080000616c75666168164e656f2e53746f726167652e476574436f6e746578746a00c3617c680f4e656f2e53746f726167652e4765746a54527ac46168164e656f2e53746f726167652e476574436f6e746578746a00c36a53c36a54c393615272680f4e656f2e53746f726167652e5075746168164e656f2e53746f726167652e476574436f6e746578740b746f74616c537570706c79617c680f4e656f2e53746f726167652e4765746a55527ac46168164e656f2e53746f726167652e476574436f6e746578740b746f74616c537570706c796a53c36a55c393615272680f4e656f2e53746f726167652e50757461006a00c36a53c3615272087472616e7366657254c168124e656f2e52756e74696d652e4e6f7469667951616c756600c56b6168164e656f2e53746f726167652e476574436f6e746578740b746f74616c537570706c79617c680f4e656f2e53746f726167652e476574616c756655c56b6c766b00527ac46c766b51527ac46c766b52527ac46a52c300a164080000616c75666a00c36168184e656f2e52756e74696d652e436865636b5769746e65737363080000616c75666a51c3c001149c63080000616c75666168164e656f2e53746f726167652e476574436f6e746578746a00c3617c680f4e656f2e53746f726167652e4765746a53527ac46a53c36a52c39f64080000616c75666a00c36a51c3907c907c9e63080051616c75666a53c36a52c39c6438006168164e656f2e53746f726167652e476574436f6e746578746a00c3617c68124e656f2e53746f726167652e44656c657465623a006168164e656f2e53746f726167652e476574436f6e746578746a00c36a53c36a52c394615272680f4e656f2e53746f726167652e5075746168164e656f2e53746f726167652e476574436f6e746578746a51c3617c680f4e656f2e53746f726167652e4765746a54527ac46168164e656f2e53746f726167652e476574436f6e746578746a51c36a54c36a52c393615272680f4e656f2e53746f726167652e507574616a00c36a51c36a52c3615272087472616e7366657254c168124e656f2e52756e74696d652e4e6f7469667951616c756651c56b6c766b00527ac46168164e656f2e53746f726167652e476574436f6e746578746a00c3617c680f4e656f2e53746f726167652e476574616c756651c56b6168134e656f2e52756e74696d652e47657454696d650480bfcf59946a00527ac46a00c300a263080000616c75666a00c3048033e101a2630d000500e8764817616c756600616c756656c56b6c766b00527ac46c766b51527ac46c766b52527ac46a51c30400e1f505966a52c3956a53527ac46168164e656f2e53746f726167652e476574436f6e746578740b746f74616c537570706c79617c680f4e656f2e53746f726167652e4765746a54527ac4070000c16ff286236a54c3946a55527ac46a55c300a1642e00616a00c36a51c3617c06726566756e6453c168124e656f2e52756e74696d652e4e6f7469667900616c75666a55c36a53c39f643f00616a00c36a53c36a55c3946a52c3960400e1f50595617c06726566756e6453c168124e656f2e52756e74696d652e4e6f746966796a55c36a53527ac46a53c3616c756653c56b61682953797374656d2e457865637574696f6e456e67696e652e476574536372697074436f6e7461696e657261681d4e656f2e5472616e73616374696f6e2e4765745265666572656e6365736a00527ac4006a51527ac46280006a00c36a51c3c36a52527ac46a52c36168154e656f2e4f75747075742e4765744173736574496461209b7cffdaa674beae0f930ebe6085af9093e5fe56b34a5c220ccdcf6efc336fc5907c907c9e6325006a52c36168184e656f2e4f75747075742e47657453637269707448617368616c75666a51c351936a51527ac46a51c36a00c3c09f637bff00616c756600c56b61682d53797374656d2e457865637574696f6e456e67696e652e476574457865637574696e6753637269707448617368616c756654c56b61682953797374656d2e457865637574696f6e456e67696e652e476574536372697074436f6e7461696e657261681a4e656f2e5472616e73616374696f6e2e4765744f757470757473006a00527ac46a51527ac4006a52527ac462aa006a51c36a52c3c36a53527ac46a53c36168184e656f2e4f75747075742e4765745363726970744861736861653eff907c907c9e636a006a53c36168154e656f2e4f75747075742e4765744173736574496461209b7cffdaa674beae0f930ebe6085af9093e5fe56b34a5c220ccdcf6efc336fc5907c907c9e6325006a00c36a53c36168134e656f2e4f75747075742e47657456616c7565936a00527ac46a52c351936a52527ac46a52c36a51c3c09f6351ff6a00c3616c756668134e656f2e436f6e74726163742e437265617465";
        ContractDescriptionProperties dp = new ContractDescriptionProperties(
                "Test",
                "0.1",
                "Guil",
                "email@email.com",
                "Anything."
        );
        ContractFunctionProperties fp = new ContractFunctionProperties(
                Arrays.asList(
                        ContractParameterType.STRING,
                        ContractParameterType.ARRAY
                ),
                ContractParameterType.BYTE_ARRAY,
                true,
                true,
                true
        );


        ContractDeploymentScript cds = NeoSerializableInterface.from(Numeric.hexStringToByteArray(contractDeploymentScript), ContractDeploymentScript.class);

        assertThat(cds.getScriptHashHexNoPrefix(), is("a69115665948d5063a3abb75b2d7ac3dc66c6d74"));
        assertThat(cds.getContractScriptHash(), is(new ScriptHash("746d6cc63dacd7b275bb3a3a06d54859661591a6")));
        assertThat(cds.getDescriptionProperties(), is(dp));
        assertThat(cds.getFunctionProperties(), is(fp));
        assertThat(cds.getScriptBinary(), is(this.contract));
    }

}
