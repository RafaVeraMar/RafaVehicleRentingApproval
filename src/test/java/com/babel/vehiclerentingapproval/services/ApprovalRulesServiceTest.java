package com.babel.vehiclerentingapproval.services;

import com.babel.vehiclerentingapproval.models.*;
import com.babel.vehiclerentingapproval.persistance.database.mappers.*;
import com.babel.vehiclerentingapproval.services.preautomaticresults.ApprovalRulesService;
import com.babel.vehiclerentingapproval.services.preautomaticresults.impl.ApprovalRulesServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

public class ApprovalRulesServiceTest {

    ApprovalRulesService service;

    private ScoringRatingMapper scoringRatingMapper;
    private EmploymentSeniorityMapper employmentSeniorityMapper;
    private InversionIngresosMapper inversionIngresosMapper;
    private PersonaMapper personaMapper;
    private RentaMapper rentaMapper;
    private SalariedMapper salariedMapper;
    private ImpagosCuotaMapper impagosCuotaMapper;
    private ApprovalClienteMapper garantiaMapper;
    private ClienteExistenteGaranteMapper clienteExistenteGaranteMapper;

    SolicitudRenting solicitud;

    Renta renta;

    @BeforeEach
    public void setUp() throws ParseException {

        this.scoringRatingMapper = Mockito.mock((ScoringRatingMapper.class));
        this.employmentSeniorityMapper = Mockito.mock((EmploymentSeniorityMapper.class));
        this.inversionIngresosMapper = Mockito.mock((InversionIngresosMapper.class));
        this.personaMapper = Mockito.mock((PersonaMapper.class));
        this.rentaMapper = Mockito.mock((RentaMapper.class));
        this.salariedMapper = Mockito.mock((SalariedMapper.class));
        this.impagosCuotaMapper = Mockito.mock((ImpagosCuotaMapper.class));
        this.garantiaMapper = Mockito.mock((ApprovalClienteMapper.class));

        this.solicitud = this.createSolicitudMock();
        this.renta = this.createRentaMock();
        this.clienteExistenteGaranteMapper = Mockito.mock(ClienteExistenteGaranteMapper.class);

        this.service = new ApprovalRulesServiceImpl(this.scoringRatingMapper, this.employmentSeniorityMapper, this.inversionIngresosMapper, this.personaMapper, this.rentaMapper, this.salariedMapper, this.impagosCuotaMapper, this.garantiaMapper, this.clienteExistenteGaranteMapper);
    }

    private SolicitudRenting createSolicitudMock() throws ParseException {
        SolicitudRenting solicitud = new SolicitudRenting();
        TipoResultadoSolicitud tipoResultadoSolicitud = new TipoResultadoSolicitud();
        Persona persona = new Persona();
        persona.setPersonaId(104);
        persona.setNombre("John");
        persona.setApellido1("Doe");
        persona.setApellido2("Doe");
        Pais pais = new Pais("ES", 724, "ESP", "España", 1);
        persona.setNacionalidad(pais);
        persona.setScoring(750);
        solicitud.setPersona(persona);
        solicitud.setInversion(10000f);
        solicitud.setCuota(500f);
        BigInteger num1 = BigInteger.valueOf(1);
        solicitud.setNumVehiculos(num1);
        BigInteger num2 = BigInteger.valueOf(36);
        solicitud.setPlazo(num2);
        solicitud.setFechaResolucion(new SimpleDateFormat("dd-MM-yyyy").parse("29-12-2020"));
        tipoResultadoSolicitud.setCodResultado("AA");
        tipoResultadoSolicitud.setDescripcion("Aprobada");
        solicitud.setTipoResultadoSolicitud(tipoResultadoSolicitud);
        return solicitud;
    }

    private Renta createRentaMock() throws ParseException {
        Renta renta = new Renta();
        Persona persona = new Persona();
        persona.setNombre("John");
        persona.setApellido1("Doe");
        persona.setApellido2("Doe");
        Pais pais = new Pais("ES", 724, "ESP", "España", 1);
        persona.setNacionalidad(pais);
        persona.setScoring(750);
        renta.setImporteBruto(10000f);
        renta.setFechaInicioEmpleo(new SimpleDateFormat("dd-MM-yyyy").parse("29-12-2016"));
        return renta;
    }

    @Test
    public void validateNationality_should_beTrue_when_ES() {
        this.solicitud.getPersona().getNacionalidad().setIsoAlfa_2("ES");
        boolean validationNationality = service.validateNationality(this.solicitud);

        Assertions.assertTrue(validationNationality);
    }

    @Test
    public void validateNationality_should_beFalse_when_NotES() {

        this.solicitud.getPersona().getNacionalidad().setIsoAlfa_2("IT");
        boolean validationNationality = service.validateNationality(this.solicitud);

        Assertions.assertFalse(validationNationality);
    }

    @Test
    public void validateNationality_should_beFalse_when_Empty() {


        this.solicitud.getPersona().getNacionalidad().setIsoAlfa_2("");
        boolean validationNationality = service.validateNationality(this.solicitud);

        Assertions.assertFalse(validationNationality);
    }

    @Test
    public void validateNationality_should_beFalse_when_Null() {
        this.solicitud.getPersona().getNacionalidad().setIsoAlfa_2(null);
        boolean validationNationality = service.validateNationality(this.solicitud);

        Assertions.assertFalse(validationNationality);
    }


    //test validateInversion
    @Test
    public void validateInversion_should_beTrue_when_BiggerThan80000() {
        Mockito.when(inversionIngresosMapper.obtenerInversionSolicitud(solicitud)).thenReturn(90000f);

        boolean validateInversion = service.validateInversion(this.solicitud);
        Assertions.assertTrue(validateInversion);
    }

    @Test
    public void validateInversion_should_beFalse_when_NotBiggerThan80000() {
        Mockito.when(inversionIngresosMapper.obtenerInversionSolicitud(solicitud)).thenReturn(10000f);
        boolean validateInversion = service.validateInversion(this.solicitud);
        Assertions.assertFalse(validateInversion);

    }

    @Test
    public void validateScoring_should_beTrue_when_minor5() {
        Mockito.when(scoringRatingMapper.obtenercScoringPersona(solicitud)).thenReturn(1f);
        boolean validateScoring = service.validateScoringPersona(this.solicitud);
        Assertions.assertTrue(validateScoring);
    }

    @Test
    public void validateScoring_should_beFalse_when_major5() {
        Mockito.when(scoringRatingMapper.obtenercScoringPersona(solicitud)).thenReturn(10f);
        boolean validateScoring = service.validateScoringPersona(this.solicitud);
        Assertions.assertFalse(validateScoring);
    }


    //test validateYearsExperience

    @Test
    public void validateYearsExperience_should_beTrue_when_yearsEmploymentBiggerThan3YearsExperience() throws ParseException {
        //(TO_DATE(CURRENT_DATE) - ra.FECHA_INICIO_EMPLEO)/365
        Mockito.when(employmentSeniorityMapper.obtenerFechaInicioEmpleoSolicitud(solicitud)).thenReturn(10f);
        //this.renta.setFechaInvalidateYearsExperienceicioEmpleo(new SimpleDateFormat("dd-MM-yyyy").parse("29-12-2016"));
        boolean validateYearsExperience = service.validateYearsExperience(this.solicitud);
        Assertions.assertTrue(validateYearsExperience);
    }

    @Test
    public void validateYearsExperience_should_beTrue_when_yearsEmploymentNotBiggerThan3YearsExperience() {
        Mockito.when(employmentSeniorityMapper.obtenerFechaInicioEmpleoSolicitud(solicitud)).thenReturn(1f);
        //this.renta.setFechaInicioEmpleo(new SimpleDateFormat("dd-MM-yyyy").parse("29-12-2016"));
        boolean validateYearsExperience = service.validateYearsExperience(this.solicitud);
        Assertions.assertFalse(validateYearsExperience);
    }


    //validateCIFCliente

    @Test
    public void validateCIFCliente_should_beTrue_when_cifSolIsEmpty() {
        Mockito.when(salariedMapper.obtenerCIFSolicitud(solicitud)).thenReturn("");
        //this.renta.setFechaInicioEmpleo(new SimpleDateFormat("dd-MM-yyyy").parse("29-12-2016"));
        boolean validateCIFCliente = service.validateCIFCliente(this.solicitud);
        Assertions.assertFalse(validateCIFCliente);

    }

    @Test
    public void validateCIFCliente_should_beTrue_when_cifSolIsNotEmpty() {
        Mockito.when(salariedMapper.obtenerCIFSolicitud(solicitud)).thenReturn("45442L");
        //this.renta.setFechaInicioEmpleo(new SimpleDateFormat("dd-MM-yyyy").parse("29-12-2016"));
        Assertions.assertDoesNotThrow(() -> {
            boolean validateCIFCliente = service.validateCIFCliente(this.solicitud);
        });
    }

    @Test
    public void validateCIFCliente_should_beFalse_when_isNull() {

        Mockito.when(salariedMapper.obtenerCIFSolicitud(solicitud)).thenReturn(null);
        Assertions.assertThrows(NullPointerException.class, () -> {
            boolean validateCIFCliente = service.validateCIFCliente(this.solicitud);
            Assertions.assertFalse(validateCIFCliente);
        });
    }

    @Test
    public void validateCIFCliente_should_beTrue_when_listaCifisEmpty() {
        Mockito.when(salariedMapper.obtenerCIFInforma()).thenReturn(new ArrayList<>());
        //this.renta.setFechaInicioEmpleo(new SimpleDateFormat("dd-MM-yyyy").parse("29-12-2016"));
        Assertions.assertThrows(NullPointerException.class, () -> {
            boolean validateCIFCliente = service.validateCIFCliente(this.solicitud);
            Assertions.assertFalse(validateCIFCliente);
        });
    }

    @Test
    public void validateCIFCliente_should_beTrue_when_listaCifisNotEmpty() {
        List<String> listaValores = new ArrayList<>();
        listaValores.add("45442L");
        listaValores.add("45442L");
        listaValores.add("45442L");
        //List<String> listaValores = Arrays.asList("45442L", "43442L", "42442L");
        Mockito.when(salariedMapper.obtenerCIFSolicitud(solicitud)).thenReturn("45442L");
        Mockito.when(salariedMapper.obtenerCIFInforma()).thenReturn(listaValores);
        //this.renta.setFechaInicioEmpleo(new SimpleDateFormat("dd-MM-yyyy").parse("29-12-2016"));
        Assertions.assertDoesNotThrow(() -> {
            boolean validateCIFCliente = service.validateCIFCliente(this.solicitud);
        });

    }

    @Test
    public void validateCIFCliente_should_beTrue_when_valorListaCifEqualsCif() {
        Mockito.when(salariedMapper.obtenerCIFSolicitud(solicitud)).thenReturn("45442L");
        List<String> listaValores = new ArrayList<>();
        listaValores.add("45442L");
        listaValores.add("45442L");
        listaValores.add("45442L");
        //List<String> listaValores = Arrays.asList("45442L", "43442L", "42442L");
        Mockito.when(salariedMapper.obtenerCIFSolicitud(solicitud)).thenReturn("45442L");
        Mockito.when(salariedMapper.obtenerCIFInforma()).thenReturn(listaValores);
        boolean validateCIFCliente = service.validateCIFCliente(this.solicitud);
        Assertions.assertTrue(validateCIFCliente);
    }

    @Test
    public void validateCIFCliente_should_beTrue_when_valorListaCifNotEqualsCif() {
        Mockito.when(salariedMapper.obtenerCIFSolicitud(solicitud)).thenReturn("45442L");
        List<String> listaValores = new ArrayList<>();
        listaValores.add("44442L");
        listaValores.add("42442L");
        listaValores.add("41442L");
        //List<String> listaValores = Arrays.asList("45442L", "43442L", "42442L");
        Mockito.when(salariedMapper.obtenerCIFSolicitud(solicitud)).thenReturn("45442L");
        Mockito.when(salariedMapper.obtenerCIFInforma()).thenReturn(listaValores);
        boolean validateCIFCliente = service.validateCIFCliente(this.solicitud);
        Assertions.assertFalse(validateCIFCliente);
    }

    @Test
    public void validateCIFCliente_should_beTrue_when_isNotNull() {
        Mockito.when(salariedMapper.obtenerCIFSolicitud(solicitud)).thenReturn("N0676766J");
        Assertions.assertDoesNotThrow(() -> {
            boolean validateCIFCliente = service.validateCIFCliente(this.solicitud);
        });
    }

    @Test
    void validateInvestmentIncome_should_beTrue_when_Investment_smallerThan_Neto() {
        Mockito.when(inversionIngresosMapper.obtenerImporteNetoRenta(solicitud)).thenReturn(11000F);
        boolean validateInvestmentIncome = service.validateInversionIngresos(this.solicitud);
        Assertions.assertTrue(validateInvestmentIncome);
    }

    @Test
    void validateInvestmentIncome_should_beTrue_when_Investment_Equals_Neto() {
        Mockito.when(inversionIngresosMapper.obtenerImporteNetoRenta(solicitud)).thenReturn(10000F);
        boolean validateInvestmentIncome = service.validateInversionIngresos(this.solicitud);
        Assertions.assertTrue(validateInvestmentIncome);

    }

    @Test
    void validateInvestmentIncome_should_beTrue_when_Investment_biggerThan_Neto() {
        Mockito.when(inversionIngresosMapper.obtenerImporteNetoRenta(solicitud)).thenReturn(9000F);
        boolean validateInvestmentIncome = service.validateInversionIngresos(this.solicitud);
        Assertions.assertFalse(validateInvestmentIncome);

    }

    @Test
    void validateNonPaymentFee_should_beFalse_when_nonPayment_smallerThan_Fee() {
        Mockito.when(impagosCuotaMapper.obtenerImporteImpagoInterno(solicitud)).thenReturn(300F);
        boolean validateNonPaymentFee = service.validateImpagoCuota(this.solicitud);
        Assertions.assertTrue(validateNonPaymentFee);
    }

    @Test
    void validateNonPaymentFee_should_beFalse_when_nonPayment_EqualsThan_Fee() {
        Mockito.when(impagosCuotaMapper.obtenerImporteImpagoInterno(solicitud)).thenReturn(500F);
        boolean validateNonPaymentFee = service.validateImpagoCuota(this.solicitud);
        Assertions.assertTrue(validateNonPaymentFee);
    }

    @Test
     void validateNonPaymentFee_should_beFalse_when_nonPayment_biggerThan_Fee() {
        Mockito.when(impagosCuotaMapper.obtenerImporteImpagoInterno(solicitud)).thenReturn(600F);
        boolean validateNonPaymentFee = service.validateImpagoCuota(this.solicitud);
        Assertions.assertFalse(validateNonPaymentFee);
    }

    //test validateClienteNoAprobadoConGarantia
    @Test
    public void validateClienteNoAprobadoConGarantia_should_beFalse_when_codResolucionIsAG_or_lessThan2YearsSinceFechaResolucion() {
        Mockito.when(garantiaMapper.existeClienteAprobadoConGarantias(anyInt())).thenReturn(0);
        boolean validateClienteNoAprobadoConGarantia = service.validateClienteNoAprobadoConGarantias(this.solicitud);
        Assertions.assertFalse(validateClienteNoAprobadoConGarantia);
    }

    @Test
    public void validateClienteNoAprobadoConGarantia_should_beTrue_when_codResolucionIsNotAG_and_moreThan2YearsSinceFechaResolucion() {
        Mockito.when(garantiaMapper.existeClienteAprobadoConGarantias(anyInt())).thenReturn(1);
        boolean validateClienteNoAprobadoConGarantia = service.validateClienteNoAprobadoConGarantias(this.solicitud);
        Assertions.assertTrue(validateClienteNoAprobadoConGarantia);
    }

    //test validateClienteNORechazadoPreviamente
    @Test
    public void validateClienteNORechazadoConGarantia_should_beFalse_when_codResolucionIsDA_or_lessThan2YearsSinceFechaResolucion() {
        Mockito.when(garantiaMapper.existeClienteRechazadoPreviamente(anyInt())).thenReturn(0);
        boolean validateClienteNoRechazadoPreviamente = service.validateClienteNoRechazadoPreviamente(this.solicitud);
        Assertions.assertFalse(validateClienteNoRechazadoPreviamente);
    }

    @Test
    public void validateClienteNORechazadoConGarantia_should_beTrue_when_codResolucionIsNotDA_and_moreThan2YearsSinceFechaResolucion() {
        Mockito.when(garantiaMapper.existeClienteRechazadoPreviamente(anyInt())).thenReturn(1);
        boolean validateClienteNoRechazadoPreviamente = service.validateClienteNoRechazadoPreviamente(this.solicitud);
        Assertions.assertTrue(validateClienteNoRechazadoPreviamente);
    }

    //test validatefindPersonasByCodResultado
    @Test
    public void validatefindPersonasByCodResultado_should_beTrue_when_clienteExiste_and_clienteEsGarante() {
        Mockito.when(clienteExistenteGaranteMapper.existeCliente(any())).thenReturn(1);
        Mockito.when(clienteExistenteGaranteMapper.clienteEsGarante(any())).thenReturn(1);
        boolean validatefindPersonasByCodResultado = service.validatefindPersonasByCodResultado(this.solicitud);
        Assertions.assertTrue(validatefindPersonasByCodResultado);
    }

    @Test
    public void validatefindPersonasByCodResultado_should_beFalse_when_clienteNoExiste_or_clienteNoEsGarante() {
        Mockito.when(clienteExistenteGaranteMapper.existeCliente(any())).thenReturn(0);
        Mockito.when(clienteExistenteGaranteMapper.clienteEsGarante(any())).thenReturn(0);
        boolean validatefindPersonasByCodResultado = service.validatefindPersonasByCodResultado(this.solicitud);
        Assertions.assertFalse(validatefindPersonasByCodResultado);
    }
}
