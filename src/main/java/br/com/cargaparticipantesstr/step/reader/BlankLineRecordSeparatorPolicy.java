package br.com.cargaparticipantesstr.step.reader;

import org.springframework.batch.item.file.separator.SimpleRecordSeparatorPolicy;

public class BlankLineRecordSeparatorPolicy extends SimpleRecordSeparatorPolicy {

    @Override
    public String postProcess(final String record) {
        if (record == null || record.isBlank()) {
            return null;
        }
        return super.postProcess(record);
    }

}