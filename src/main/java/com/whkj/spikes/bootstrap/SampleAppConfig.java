package com.whkj.spikes.bootstrap;


import com.whkj.spikes.bo.MyPerson;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;



@Configuration
@Import(value = {MyPerson.class,MyImportSelector.class})
public class SampleAppConfig {


}
