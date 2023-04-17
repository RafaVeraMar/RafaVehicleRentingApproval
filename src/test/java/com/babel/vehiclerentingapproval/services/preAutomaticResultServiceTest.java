package com.babel.vehiclerentingapproval.services;

import com.babel.vehiclerentingapproval.models.Pais;
import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.models.Renta;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.persistance.database.mappers.*;
import com.babel.vehiclerentingapproval.services.preautomaticresults.ApprovalRulesService;
import com.babel.vehiclerentingapproval.services.preautomaticresults.CalculateAutomaticResult;
import com.babel.vehiclerentingapproval.services.preautomaticresults.DenyRulesService;
import com.babel.vehiclerentingapproval.services.preautomaticresults.impl.ApprovalRulesServiceImpl;
import com.babel.vehiclerentingapproval.services.preautomaticresults.impl.CalculateAutomaticResultImpl;
import com.babel.vehiclerentingapproval.services.preautomaticresults.impl.DenyRulesServicesImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;

class preAutomaticResultServiceTest {
    ApprovalRulesService approvalRulesService;
    DenyRulesService denyRulesService;
    CalculateAutomaticResult calculateAutomaticResult;
    private ClienteExistenteGaranteMapper clienteExistenteGaranteMapper;
    private ScoringRatingMapper scoringRatingMapper;
    private EmploymentSeniorityMapper employmentSeniorityMapper;
    private InversionIngresosMapper inversionIngresosMapper;
    private PersonaMapper personaMapper;
    private RentaMapper rentaMapper;
    private SalariedMapper salariedMapper;
    private ImpagosCuotaMapper impagosCuotaMapper;
    private ApprovalClienteMapper garantiaMapper;
    private AutomaticResultMapper automaticResultMapper;
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
        this.clienteExistenteGaranteMapper = Mockito.mock((ClienteExistenteGaranteMapper.class));

        this.solicitud = this.createSolicitudMock();
        this.renta = this.createRentaMock();
        this.denyRulesService = new DenyRulesServicesImpl();

        this.approvalRulesService = new ApprovalRulesServiceImpl(this.scoringRatingMapper,
                this.employmentSeniorityMapper, this.inversionIngresosMapper, this.personaMapper,
                this.rentaMapper, this.salariedMapper, this.impagosCuotaMapper, this.garantiaMapper, this.clienteExistenteGaranteMapper);
        this.calculateAutomaticResult = new CalculateAutomaticResultImpl(this.denyRulesService,this.approvalRulesService,this.automaticResultMapper);
    }

    private SolicitudRenting createSolicitudMock() {
        SolicitudRenting solicitud = new SolicitudRenting();
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
        return solicitud;
    }

    /**
     * Este metodo no seq
     * @return Renta que es
     * @throws ParseException esto se lanza en el caso de...
     */
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
    public void validateFindAnyDeny_shouldBeTrue_whenAllFalse() throws ParseException {
        this.solicitud.getPersona().setFechaNacimiento(new SimpleDateFormat("dd-MM-yyyy").parse("13-06-2000"));
        this.solicitud.getPersona().setScoring(1);
        BigInteger num2 = BigInteger.valueOf(10);
        this.solicitud.setPlazo(num2);
        boolean validateFindAnyDeny = this.calculateAutomaticResult.findAnyDeny(solicitud);

        Assertions.assertTrue(validateFindAnyDeny);

    }
    @Test
    public void validateFindAnyDeny_shouldBeFalse_whenAnyTrue() throws ParseException {
        this.solicitud.getPersona().setFechaNacimiento(new SimpleDateFormat("dd-MM-yyyy").parse("13-06-2016"));
        this.solicitud.getPersona().setScoring(1);
        BigInteger num2 = BigInteger.valueOf(10);
        this.solicitud.setPlazo(num2);
        boolean validateFindAnyDeny = this.calculateAutomaticResult.findAnyDeny(solicitud);

        Assertions.assertFalse(validateFindAnyDeny);

    }

    @Test
    public void validateFindAllApproval_shouldBeTrue_whenAllTrue() throws ParseException {
        this.solicitud.setInversion(10000f);
        Mockito.when(inversionIngresosMapper.obtenerImporteNetoRenta(solicitud)).thenReturn(90000f);
        Mockito.when(inversionIngresosMapper.obtenerInversionSolicitud(solicitud)).thenReturn(90000f);
        Mockito.when(scoringRatingMapper.obtenercScoringPersona(solicitud)).thenReturn(3f);
        Mockito.when(impagosCuotaMapper.obtenerImporteImpagoInterno(solicitud)).thenReturn(10f);
        this.solicitud.setCuota(20f);
        List<String> listaValores = new ArrayList<>();
        listaValores.add("45442L");
        listaValores.add("45442L");
        listaValores.add("45442L");
        Mockito.when(salariedMapper.obtenerCIFSolicitud(solicitud)).thenReturn("45442L");
        Mockito.when(salariedMapper.obtenerCIFInforma()).thenReturn(listaValores);
        this.solicitud.getPersona().getNacionalidad().setIsoAlfa_2("ES");
        Mockito.when(employmentSeniorityMapper.obtenerFechaInicioEmpleoSolicitud(solicitud)).thenReturn(10f);
        Mockito.when(garantiaMapper.existeClienteAprobadoConGarantias(anyInt())).thenReturn(1);
        Mockito.when(garantiaMapper.existeClienteRechazadoPreviamente(anyInt())).thenReturn(1);

       boolean validateFindAllApproval = this.calculateAutomaticResult.findAllApproval(solicitud);

        Assertions.assertTrue(validateFindAllApproval);

    }
    @Test
    public void validateFindAllApproval_shouldBeFalse_whenAnyFalse() throws ParseException {
        this.solicitud.setInversion(90000f);
        Mockito.when(inversionIngresosMapper.obtenerImporteNetoRenta(solicitud)).thenReturn(90000f);
        Mockito.when(inversionIngresosMapper.obtenerInversionSolicitud(solicitud)).thenReturn(90000f);
        Mockito.when(scoringRatingMapper.obtenercScoringPersona(solicitud)).thenReturn(8f);
        Mockito.when(impagosCuotaMapper.obtenerImporteImpagoInterno(solicitud)).thenReturn(10f);
        this.solicitud.setCuota(20f);
        List<String> listaValores = new ArrayList<>();
        listaValores.add("45442L");
        listaValores.add("45442L");
        listaValores.add("45442L");
        Mockito.when(salariedMapper.obtenerCIFSolicitud(solicitud)).thenReturn("45442L");
        Mockito.when(salariedMapper.obtenerCIFInforma()).thenReturn(listaValores);
        this.solicitud.getPersona().getNacionalidad().setIsoAlfa_2("ES");
        Mockito.when(employmentSeniorityMapper.obtenerFechaInicioEmpleoSolicitud(solicitud)).thenReturn(10f);
        Mockito.when(garantiaMapper.existeClienteAprobadoConGarantias(anyInt())).thenReturn(1);
        Mockito.when(garantiaMapper.existeClienteRechazadoPreviamente(anyInt())).thenReturn(1);

        boolean validateFindAllApproval = this.calculateAutomaticResult.findAllApproval(solicitud);

        Assertions.assertFalse(validateFindAllApproval);

    }

    @Test
    public void validateFindAnyApproval_shouldBeTrue_whenAnyTrue() throws ParseException {
        this.solicitud.setInversion(10000f);
        Mockito.when(inversionIngresosMapper.obtenerImporteNetoRenta(solicitud)).thenReturn(90000f);
        Mockito.when(inversionIngresosMapper.obtenerInversionSolicitud(solicitud)).thenReturn(90000f);
        Mockito.when(scoringRatingMapper.obtenercScoringPersona(solicitud)).thenReturn(3f);
        Mockito.when(impagosCuotaMapper.obtenerImporteImpagoInterno(solicitud)).thenReturn(10f);
        this.solicitud.setCuota(20f);
        List<String> listaValores = new ArrayList<>();
        listaValores.add("45442L");
        listaValores.add("45442L");
        listaValores.add("45442L");
        Mockito.when(salariedMapper.obtenerCIFSolicitud(solicitud)).thenReturn("45442L");
        Mockito.when(salariedMapper.obtenerCIFInforma()).thenReturn(listaValores);
        this.solicitud.getPersona().getNacionalidad().setIsoAlfa_2("ES");
        Mockito.when(employmentSeniorityMapper.obtenerFechaInicioEmpleoSolicitud(solicitud)).thenReturn(10f);
        Mockito.when(garantiaMapper.existeClienteAprobadoConGarantias(anyInt())).thenReturn(1);
        Mockito.when(garantiaMapper.existeClienteRechazadoPreviamente(anyInt())).thenReturn(1);

        boolean validateFindAnyApproval = this.calculateAutomaticResult.findAnyApproval(solicitud);

        Assertions.assertTrue(validateFindAnyApproval);

    }
    @Test
    public void validateFindAnyApproval_shouldBeFalse_whenAnyFalse() throws ParseException {
        this.solicitud.setInversion(90000f);
        Mockito.when(inversionIngresosMapper.obtenerImporteNetoRenta(solicitud)).thenReturn(90000f);
        Mockito.when(inversionIngresosMapper.obtenerInversionSolicitud(solicitud)).thenReturn(90000f);
        Mockito.when(scoringRatingMapper.obtenercScoringPersona(solicitud)).thenReturn(8f);
        Mockito.when(impagosCuotaMapper.obtenerImporteImpagoInterno(solicitud)).thenReturn(10f);
        this.solicitud.setCuota(20f);
        List<String> listaValores = new ArrayList<>();
        listaValores.add("45442L");
        listaValores.add("45442L");
        listaValores.add("45442L");
        Mockito.when(salariedMapper.obtenerCIFSolicitud(solicitud)).thenReturn("45442L");
        Mockito.when(salariedMapper.obtenerCIFInforma()).thenReturn(listaValores);
        this.solicitud.getPersona().getNacionalidad().setIsoAlfa_2("ES");
        Mockito.when(employmentSeniorityMapper.obtenerFechaInicioEmpleoSolicitud(solicitud)).thenReturn(10f);
        Mockito.when(garantiaMapper.existeClienteAprobadoConGarantias(anyInt())).thenReturn(1);
        Mockito.when(garantiaMapper.existeClienteRechazadoPreviamente(anyInt())).thenReturn(1);

        boolean validateFindAnyApproval = this.calculateAutomaticResult.findAllApproval(solicitud);

        Assertions.assertFalse(validateFindAnyApproval);

    }

    public void validateTotalResult() throws ParseException {
        this.solicitud.getPersona().setFechaNacimiento(new SimpleDateFormat("dd-MM-yyyy").parse("13-06-2000"));
        this.solicitud.getPersona().setScoring(1);
        BigInteger num2 = BigInteger.valueOf(10);
        this.solicitud.setPlazo(num2);
        ////


    }

}

