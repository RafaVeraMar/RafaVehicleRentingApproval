package com.babel.vehiclerentingapproval.services.preautomaticresults.impl;

import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.models.SolicitudRenting;
import com.babel.vehiclerentingapproval.persistance.database.mappers.*;
import com.babel.vehiclerentingapproval.services.preautomaticresults.ApprovalRulesService;
import org.springframework.stereotype.Service;

import java.util.List;
/**

 * Esta clase contiene las reglas de aprobación de una solicitud de renting, las cuales todas ellas se tienen que cumplir
 * @author: Ismael Mesa Zyout
 * @author: Álvaro Dorado Navas
 * @author: Rafael Vera Marañon
 * @author: Daniel Gallardo Carrión
 */
@Service
public class ApprovalRulesServiceImpl implements ApprovalRulesService {

    /**
     * Contiene un mapper que realiza las acciones relacionadas con el scoring de una persona
     */
    private ScoringRatingMapper scoringRatingMapper;
    /**
     * Contiene un mapper que realiza las acciones relacionadas con la fecha de inicio de empleo de una persona
     */
    private EmploymentSeniorityMapper employmentSeniorityMapper;
    /**
     * Contiene un mapper que realiza las acciones relacionadas con la inversion de una solicitud de renting
     */
    private InversionIngresosMapper inversionIngresosMapper;
    /**
     * Contiene un mapper que realiza las acciones relacionadas con una persona
     */
    private PersonaMapper personaMapper;
    /**
     * Contiene un mapper que realiza las acciones relacionadas con una renta
     */

    private RentaMapper rentaMapper;
    /**
     * Contiene un mapper que realiza las acciones relacionadas con el salario de una persona
     */
    private SalariedMapper salariedMapper;
    /**
     * Contiene un mapper que realiza las acciones relacionadas con el impago de una persona
     */
    private ImpagosCuotaMapper impagosCuotaMapper;
    /**
     * Contiene un mapper que realiza las acciones relacionadas con las aprobaciones de la solicitud renting de un cliente
     */
    private ApprovalClienteMapper approvalClienteMapper;
    /**
     * Contiene un mapper que realiza las acciones relacionadas con el garante de una persona
     */
    private ClienteExistenteGaranteMapper clienteExistenteGaranteMapper;
    /**
     * Constante que contiene la minima inversión que puede tener una solicitud renting para cumplir la regla
     */
    private static final int inversionMayor = 80000;
    /**
     * Constante que contiene el scoring que la persona no puede superar para cumplir la regla
     */
    private static final int scoringRating = 5;

    /**
     * Constructor de la clase approvalRulesServiceImpl que inicializa los atributos de la clase
     */
    public ApprovalRulesServiceImpl(ScoringRatingMapper scoringRatingMapper, EmploymentSeniorityMapper employmentSeniorityMapper, InversionIngresosMapper inversionIngresosMapper, PersonaMapper personaMapper, RentaMapper rentaMapper, SalariedMapper salariedMapper, ImpagosCuotaMapper impagosCuotaMapper, ApprovalClienteMapper approvalClienteMapper, ClienteExistenteGaranteMapper clienteExistenteGaranteMapper) {
        this.scoringRatingMapper = scoringRatingMapper;
        this.employmentSeniorityMapper = employmentSeniorityMapper;
        this.inversionIngresosMapper = inversionIngresosMapper;
        this.personaMapper = personaMapper;
        this.rentaMapper = rentaMapper;
        this.salariedMapper = salariedMapper;
        this.impagosCuotaMapper = impagosCuotaMapper;
        this.approvalClienteMapper = approvalClienteMapper;
        this.clienteExistenteGaranteMapper = clienteExistenteGaranteMapper;
    }
    /**
     * Método que comprueba si la inversion de una solicitud de renting es menor o igual que el importe neto de una renta para esa solicitud de renting
     * @param solicitudRenting El parámetro solicitudRenting define una solicitud de renting
     * @return true si es menor o igual que el importe neto y false en caso contrario
     */
    @Override
    public Boolean validateInversionIngresos(SolicitudRenting solicitudRenting) {
        if (solicitudRenting.getInversion()
                <= this.inversionIngresosMapper.obtenerImporteNetoRenta(solicitudRenting)) {
            return true;

        } else {
            return false;
        }
    }
    /**
     * Método que comprueba si la inversion de una solicitud es mayor que la inversionMayor establecida
     * @param solicitudRenting El parámetro solicitudRenting define una solicitud de renting
     * @return true si es mayor que la inversionMayor establecida y false en caso contrario
     */
    @Override
    public Boolean validateInversion(SolicitudRenting solicitudRenting) {
        return this.inversionIngresosMapper.obtenerInversionSolicitud(solicitudRenting) > inversionMayor;
    }
    /**
     * Método que comprueba si el scoring de un cliente es menor que el rating de scoring establecido
     * @param solicitudRenting El parámetro solicitudRenting define una solicitud de renting
     * @return true si es menor que el rating de scoring establecido y false en caso contrario
     */
    @Override
    public Boolean validateScoringPersona(SolicitudRenting solicitudRenting) {
        float valorScoring = this.scoringRatingMapper.obtenercScoringPersona(solicitudRenting);
        if (valorScoring < scoringRating) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Método que comprueba si el impago interno de un cliente es menor o igual que la cuota de una solicitud de renting
     * @param solicitudRenting El parámetro solicitudRenting define una solicitud de renting
     * @return true si es menor o igual que la cuota de una solicitud de renting y false en caso contrario
     */
    @Override
    public Boolean validateImpagoCuota(SolicitudRenting solicitudRenting) {
        if (this.impagosCuotaMapper.obtenerImporteImpagoInterno(solicitudRenting)
                <= solicitudRenting.getCuota()) {

            return true;

        } else {
            return false;
        }
    }
    /**
     * Método que comprueba si el cif de empleador de un cliente esta contenido en la lista de de cif de Informa
     * @param solicitudRenting El parámetro solicitudRenting define una solicitud de renting
     * @return true si esta contenido en la lista de de cif de Informa y false en caso contrario
     */
    @Override
    public Boolean validateCIFCliente(SolicitudRenting solicitudRenting) {
        boolean encontrado = false;
        String cadena;
        String cifSol = this.salariedMapper.obtenerCIFSolicitud(solicitudRenting);
        List<String> listaCIF = this.salariedMapper.obtenerCIFInforma();
        if (!cifSol.isEmpty() && cifSol != null) {
            for (String cif : listaCIF) {
                cadena = cif.trim();
                if (cadena.equals(cifSol)) {
                    encontrado = true;
                }
            }
            return encontrado;
        } else {
            return false;
        }
    }
    /**
     * Método que comprueba si la nacionalidad de un cliente es española
     * @param solicitudRenting El parámetro solicitudRenting define una solicitud de renting
     * @return true si la nacionalidad de la persona es española y false en caso contrario
     */
    @Override
    public Boolean validateNationality(SolicitudRenting solicitudRenting) {
        String nacionalidad = solicitudRenting.getPersona().getNacionalidad().getIsoAlfa_2();
        boolean espanol = false;

        if (nacionalidad != null && nacionalidad.equalsIgnoreCase("ES")) {
            espanol = true;
        }

        return espanol;
    }

    /**
     * Método que comprueba si los años de empleo de un cliente es mayor o igual a los años establecidos
     * @param solicitudRenting El parámetro solicitudRenting define una solicitud de renting
     * @return true si es mayor o igual a los años establecidos y false en caso contrario
     */
    @Override
    public Boolean validateYearsExperience(SolicitudRenting solicitudRenting) {
        float yearsEmployment = this.employmentSeniorityMapper.obtenerFechaInicioEmpleoSolicitud(solicitudRenting);
        if (yearsEmployment >= 3) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Método que comprueba si las garantias de un cliente han sido aprobadas
     * @param solicitudRenting El parámetro solicitudRenting define una solicitud de renting
     * @return true si las garantias de un cliente han sido aprobadas y false en caso contrario
     */
    public Boolean validateClienteNoAprobadoConGarantias(SolicitudRenting solicitudRenting) {
        Persona persona = solicitudRenting.getPersona();
        int aprobado = this.approvalClienteMapper.existeClienteAprobadoConGarantias(persona.getPersonaId());
        if (aprobado == 0) {
            return false;
        } else {
            return true;
        }
    }
    /**
     * Método que comprueba si la cliente ha sido rechazado previamente
     * @param solicitudRenting El parámetro solicitudRenting define una solicitud de renting
     * @return true si si la persona ha sido rechazado previamente y false en caso contrario
     */
    public Boolean validateClienteNoRechazadoPreviamente(SolicitudRenting solicitudRenting) {
        Persona persona = solicitudRenting.getPersona();
        int aprobado = this.approvalClienteMapper.existeClienteRechazadoPreviamente(persona.getPersonaId());
        if (aprobado == 0) {
            return false;
        } else {
            return true;
        }
    }
    /**
     * Método que comprueba si existe un cliente y si existe un garante para un cliente
     * @param solicitudRenting El parámetro solicitudRenting define una solicitud de renting
     * @return true si existe un cliente y si existe un garante para un cliente y false en caso contrario
     */
    @Override
    public Boolean validatefindPersonasByCodResultado(SolicitudRenting solicitudRenting) {
        int existeCliente = this.clienteExistenteGaranteMapper.existeCliente(solicitudRenting.getFechaSolicitud());
        int clienteEsGarante = this.clienteExistenteGaranteMapper.clienteEsGarante(solicitudRenting.getPersona().getNif());

        if (existeCliente == 1 || clienteEsGarante == 1) {
            return true;
        } else {
            return false;
        }
    }

}
