@Entity
@SequenceGenerator(initialValue = 1, name = "S_TEST", sequenceName = "S_TEST")
@DynamicUpdate(true)
@Table(
		name = "TEST", 
		uniqueConstraints = @UniqueConstraint(columnNames = {"code"}),
		indexes={
			@Index(name="TEST_SUPPLIER_ID_COMPANYID",columnList = "SUPPLIER_ID,COMPANY_ID"),
			@Index(name="PRENDRE_FK",columnList="TEST_STATUS_CODE"),
			@Index(name="SUPPLIER_TEST_FK",columnList="SUPPLIER_ID"),
			@Index(name="TOTAL_AMOUNT",columnList="TOTAL_AMOUNT"),
			@Index(name="SITE",columnList="SITE"),
			@Index(name="TYPE_TEST",columnList="`TYPE`"),
			@Index(name="DATE_TEST",columnList="`DATE`"),
			@Index(name="CURRENCY_TEST",columnList="CURRENCY"),
			@Index(name="SCAN_DATE",columnList="SCAN_DATE"),
			@Index(name="BATCH_NAME",columnList="BATCH_NAME"),
			@Index(name="TOTAL_NET_AMOUNT",columnList="TOTAL_NET_AMOUNT"),
			@Index(name="DUE_DATE",columnList="DUE_DATE"),
			@Index(name="DATE_CHANGE_STATE",columnList="DATE_CHANGE_STATE"),
			@Index(name="TEST_STATUS_SUPPLIER",columnList="TEST_STATUS_CODE,SUPPLIER_ID")
			
		}
)
public class Test implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * The index. 

	 * id

	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "S_TEST")
	@Column(name = "TEST_ID")
	@FilterOperator(operator = QueryOperator.EQUALS)
	private Integer id;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "COMPANY_SUPPLIER", indexes = {
			@Index(name = "RELIE_FK", columnList = "SUPPLIER_ID"),
			@Index(name = "RELIE4_FK", columnList = "COMPANY_ID") }, joinColumns = @JoinColumn(name = "COMPANY_ID", nullable = false), inverseJoinColumns = @JoinColumn(name = "SUPPLIER_ID", nullable = false))
	private List<Supplier> suppliers = new ArrayList<Supplier>();
	
	/**
	 * status
	 */
	@Column(name = "TEST_STATUS_CODE", nullable = false)
	@FilterOperator(operator = QueryOperator.EQUALS)
	private String status;
	
	/**
	 * The supplier id. 

	 * supplier

	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "SUPPLIER_ID", nullable = false)
	@Fetch(FetchMode.JOIN)
	@BatchSize(size = 20)
	@FilterOperator(operator = QueryOperator.EQUALS)
	@XmlPath(".")
	private Supplier supplier;
