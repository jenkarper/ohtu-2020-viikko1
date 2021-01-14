package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastollaEiNegatiivistaTilavuutta() {
        Varasto negaTilavuus = new Varasto(-1);
        assertEquals(0, negaTilavuus.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastollaEiNegatiivistaSaldoa() {
        Varasto negaSaldo = new Varasto(10, -1);
        assertEquals(0, negaSaldo.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void kuormitettuKonstruktoriLuoEpatyhjanVaraston() {
        Varasto epatyhja = new Varasto(10, 3);
        assertEquals(3, epatyhja.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void kuormitettuKonstruktoriEiYlitaTilavuutta() {
        Varasto epatyhja = new Varasto(10, 10.1);
        assertEquals(10, epatyhja.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void kuormitettuKonstruktoriEiLuoNegatiivistaTilavuutta() {
        Varasto negaTilavuus = new Varasto(-1, -1);
        assertEquals(0, negaTilavuus.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysEiHyvaksyNegatiivistaArvoa() {
        varasto.lisaaVarastoon(2);
        varasto.lisaaVarastoon(-1);
        assertEquals(2, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysEiYlikuormitaVarastoa() {
        varasto.lisaaVarastoon(20);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenEiHyvaksyNegatiivistaArvoa() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(-1);
        
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastostaEiVoiOttaaMaaraansaEnempaa() {
        varasto.lisaaVarastoon(5);
        double saatu = varasto.otaVarastosta(10);
        
        assertEquals(5, saatu, vertailuTarkkuus);
    }
    
    @Test
    public void saldonYlittavaOttoNollaaSaldon() {
        varasto.lisaaVarastoon(7);
        varasto.otaVarastosta(10);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void merkkijonoesitysMuodostuuOikein() {
        varasto.lisaaVarastoon(3.25);
        String odotettu = "saldo = 3.25, vielä tilaa 6.75";
        
        assertEquals(odotettu, varasto.toString());
    }

}