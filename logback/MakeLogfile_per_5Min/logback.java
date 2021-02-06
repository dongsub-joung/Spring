@Component
public class FiveMinAppender<E> extends RollingFileAppender<E> {

    public static ProjectProperties projectProperties;
    private static long nextMillis = -1;
    private int rollOverTimeInMinutes = 5;

    @Autowired
    public void setProjectProperties(ProjectProperties projectProperties) {
        this.projectProperties = projectProperties;
        rollOverTimeInMinutes = Integer.parseInt(Optional.ofNullable(projectProperties.getProperties().get("fileSplitMinute")).orElse("5"));
        init();
    }

    private void init() {
        nextMillis = nextMillis();
    }


    public long nextMillis() {
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(new Date());
        calendar.set(Calendar.MILLISECOND, 0);

        // seconds
        calendar.set(Calendar.SECOND, 0);
        // minute
        calendar.add(Calendar.MINUTE, rollOverTimeInMinutes);
        int minute = calendar.get(Calendar.MINUTE);
        minute -= calendar.get(Calendar.MINUTE) % rollOverTimeInMinutes;
        calendar.set(Calendar.MINUTE, minute);

        return calendar.getTimeInMillis();
    }

    @Override
    public void rollover() {
        long currentTime = System.currentTimeMillis();
     
        if (nextMillis > 0 && currentTime >= nextMillis) {
            nextMillis = nextMillis();
            super.rollover();
        }


    }

    @Override
    protected void subAppend(E event) {
        super.subAppend(event);
    }
}