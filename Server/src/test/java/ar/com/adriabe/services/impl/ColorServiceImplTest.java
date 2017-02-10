package ar.com.adriabe.services.impl;

import ar.com.adriabe.components.PasswordRecoverer;
import ar.com.adriabe.model.Color;
import ar.com.adriabe.services.ColorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/application-context.xml" })
public class ColorServiceImplTest {

    @Autowired
    ColorService colorService;

    @Autowired
    @Qualifier("passwordRecoverer")
    PasswordRecoverer recoverer;

    @Test
    public void testSave() throws Exception {
        Color color = new Color();
        color.setCode("32132");
        color.setCoordinate("FFFF");
        color.setMethod("dsadasda");
        color.setName("dasdadas3212da");

        //colorService.save(color);

        recoverer.recover("admin", "alejandro.mildiner@gmail.com");
    }


}