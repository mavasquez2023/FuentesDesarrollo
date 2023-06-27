package cl.araucana.independientes.vo;

/* SolicitudNegocioVO.
 * Corresponde a una superclase que aloja a todas las demás clases del sistema.
 * Se usa principalmente en tareas de insert y select de los datos del sistema.
 * Sus variables se usan de la siguiente forma:
 * 		.- resultado: contiene el resultado de error en caso de que una consulta no haya terminado de forma exitosa.
 * 		.- codResultado: contiene el codigo del resultado dependiendo del tipo de consulta.
 * 		.- afiliadoVO: objeto que contiene toda la informacion del objeto AfiliadoVO.
 * 		.- analistaVO: objeto que contiene toda la informacion del objeto AnalistaVO.
 * 		.- direccionPartVO: objeto que contiene toda la informacion  de direccion particular del objeto DireccionPartVO.
 * 		.- direccionComerVO: objeto que contiene toda la informacion de direccion comercial del objeto DireccionComerVO.
 * 		.- emailVO: objeto que contiene toda la informacion del objeto EmailVO.
 * 		.- grupoFamiliarVO : objeto que contiene toda la informacion del objeto GrupoFamiliarVO.
 * 		.- ingresoEconomicoVO: objeto que contiene toda la informacion del objeto IngresoEconomicoVO.
 * 		.- personaVO: objeto que contiene toda la informacion del objeto PersonaVO.
 * 		.- solicitudVO: objeto que contiene toda la informacion del objeto SolicitudVO.
 * 		.- telefonoPartVO: objeto que contiene toda la informacion de telefono particluar del objeto TelefonoPartVO.
 * 		.- telefonoCeluVO: objeto que contiene toda la informacion de telefono celular objeto TelefonoCeluVO.
 * 		.- telefonoComerVO: objeto que contiene toda la informacion de telefono comercial del objeto TelefonoComerVO.
 * 		.- analistaCaptadorVO: objeto que contiene toda la informacion del objeto analistaCaptadorVO similar al objeto AnalistaVO.
 * */
public class SolicitudNegocioVO {

    private String resultado;
    private int codResultado;

    /*Declaracion de variables de la clase SolicitudNegocioVO*/
    private AfiliadoVO afiliadoVO;
    private AgrupacionVO agrupacionVO;
    private AnalistaVO analistaVO;
    private BeneficiarioVO beneficiarioVO;
    private DireccionVO direccionPartVO;
    private DireccionVO direccionComerVO;
    private EmailVO emailVO;
    private EmailVO emailComerVO;	
    private GrupoFamiliarVO grupoFamiliarVO;
    private IngresoEconomicoVO ingresoEconomicoVO;
    private PersonaVO personaVO;
    private SolicitudVO solicitudVO;
    private TelefonoVO telefonoPartVO;
    private TelefonoVO telefonoCeluVO;
    private TelefonoVO telefonoComerVO;
    private AnalistaVO analistaCaptadorVO;
    private DocumentoVO[] listaDocumentoVO;

    /*Creación de los Getting and Setting de la clase.*/
    public AfiliadoVO getAfiliadoVO() {
        return afiliadoVO;
    }
    public void setAfiliadoVO(AfiliadoVO afiliadoVO) {
        this.afiliadoVO = afiliadoVO;
    }
    public AgrupacionVO getAgrupacionVO() {
        return agrupacionVO;
    }
    public void setAgrupacionVO(AgrupacionVO agrupacionVO) {
        this.agrupacionVO = agrupacionVO;
    }
    public AnalistaVO getAnalistaCaptadorVO() {
        return analistaCaptadorVO;
    }
    public void setAnalistaCaptadorVO(AnalistaVO analistaCaptadorVO) {
        this.analistaCaptadorVO = analistaCaptadorVO;
    }
    public AnalistaVO getAnalistaVO() {
        return analistaVO;
    }
    public void setAnalistaVO(AnalistaVO analistaVO) {
        this.analistaVO = analistaVO;
    }
    public BeneficiarioVO getBeneficiarioVO() {
        return beneficiarioVO;
    }
    public void setBeneficiarioVO(BeneficiarioVO beneficiarioVO) {
        this.beneficiarioVO = beneficiarioVO;
    }
    public int getCodResultado() {
        return codResultado;
    }
    public void setCodResultado(int codResultado) {
        this.codResultado = codResultado;
    }
    public DireccionVO getDireccionComerVO() {
        return direccionComerVO;
    }
    public void setDireccionComerVO(DireccionVO direccionComerVO) {
        this.direccionComerVO = direccionComerVO;
    }
    public DireccionVO getDireccionPartVO() {
        return direccionPartVO;
    }
    public void setDireccionPartVO(DireccionVO direccionPartVO) {
        this.direccionPartVO = direccionPartVO;
    }
    public EmailVO getEmailVO() {
        return emailVO;
    }
    public void setEmailVO(EmailVO emailVO) {
        this.emailVO = emailVO;
    }
    public GrupoFamiliarVO getGrupoFamiliarVO() {
        return grupoFamiliarVO;
    }
    public void setGrupoFamiliarVO(GrupoFamiliarVO grupoFamiliarVO) {
        this.grupoFamiliarVO = grupoFamiliarVO;
    }
    public IngresoEconomicoVO getIngresoEconomicoVO() {
        return ingresoEconomicoVO;
    }
    public void setIngresoEconomicoVO(IngresoEconomicoVO ingresoEconomicoVO) {
        this.ingresoEconomicoVO = ingresoEconomicoVO;
    }
    public DocumentoVO[] getListaDocumentoVO() {
        return listaDocumentoVO;
    }
    public void setListaDocumentoVO(DocumentoVO[] listaDocumentoVO) {
        this.listaDocumentoVO = listaDocumentoVO;
    }
    public PersonaVO getPersonaVO() {
        return personaVO;
    }
    public void setPersonaVO(PersonaVO personaVO) {
        this.personaVO = personaVO;
    }
    public String getResultado() {
        return resultado;
    }
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    public SolicitudVO getSolicitudVO() {
        return solicitudVO;
    }
    public void setSolicitudVO(SolicitudVO solicitudVO) {
        this.solicitudVO = solicitudVO;
    }
    public TelefonoVO getTelefonoCeluVO() {
        return telefonoCeluVO;
    }
    public void setTelefonoCeluVO(TelefonoVO telefonoCeluVO) {
        this.telefonoCeluVO = telefonoCeluVO;
    }
    public TelefonoVO getTelefonoComerVO() {
        return telefonoComerVO;
    }
    public void setTelefonoComerVO(TelefonoVO telefonoComerVO) {
        this.telefonoComerVO = telefonoComerVO;
    }
    public TelefonoVO getTelefonoPartVO() {
        return telefonoPartVO;
    }
    public void setTelefonoPartVO(TelefonoVO telefonoPartVO) {
        this.telefonoPartVO = telefonoPartVO;
    }
    public EmailVO getEmailComerVO() {
        return emailComerVO;
    }
    public void setEmailComerVO(EmailVO emailComerVO) {
        this.emailComerVO = emailComerVO;
    }

}
