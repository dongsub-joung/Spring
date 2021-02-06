@Slf4j
@RestController
@RequestMapping(NotificationsController.URI_PREFIX)
@Api(tags = "알림")
public class NotificationsController {
    public static final String URI_PREFIX = ApisController.URI_PREFIX + "/notifications";

    @Autowired
    FcmService fcmService;

    @Autowired
    CoreUserService coreUserService;


    @Value("${project.properties.firebase-multicast-message-size}")
    Long multicastMessageSize;


    @ApiOperation(value = "토픽푸쉬")
    @PostMapping(value = "/pushs/topics/{topic}")
    public void notificationTopics(@PathVariable("topic") String topic, @RequestBody RequestPushMessage data) throws FirebaseMessagingException {
        Notification notification = Notification.builder().setTitle(data.getTitle()).setBody(data.getBody()).setImage(data.getImage()).build();
        Message.Builder builder = Message.builder();
        Optional.ofNullable(data.getData()).ifPresent(sit -> builder.putAllData(sit));
        Message msg = builder.setTopic(topic).setNotification(notification).build();
        fcmService.sendMessage(msg);
    }

    @ApiOperation(value = "전고객푸쉬")
    @PostMapping(value = "/pushs/users")
    public void notificationUsers(@RequestBody RequestPushMessage data) throws IOException, FirebaseMessagingException {
        List<CoreUser> targetUser = null == data.getUserNos() ? coreUserService.findAllByEnabledAndPushTokenIsNotNull(UseCd.USE001) : coreUserService.findAllByEnabledAndPushTokenIsNotNullAndNoIn(UseCd.USE001, data.getUserNos());
        AtomicInteger counter = new AtomicInteger();
        Collection<List<CoreUser>> sendUserGroups = targetUser.stream().collect(Collectors.groupingBy(it -> counter.getAndIncrement() / multicastMessageSize.longValue())).values();
        for (List<CoreUser> it : sendUserGroups) {
            Notification notification = Notification.builder().setTitle(data.getTitle()).setBody(data.getBody()).setImage(data.getImage()).build();
            MulticastMessage.Builder builder = MulticastMessage.builder();
            Optional.ofNullable(data.getData()).ifPresent(sit -> builder.putAllData(sit));
            MulticastMessage message = builder
                    .setNotification(notification)
                    .addAllTokens(it.stream().map(sit -> sit.getPushToken()).collect(Collectors.toList()))
                    .build();
            this.fcmService.sendMessage(message);
        }
    }


    @ApiOperation(value = "특정 고객푸쉬")
    @PostMapping(value = "/pushs/users/{no}")
    public void notificationUser(@PathVariable("no") Long no, @RequestBody RequestPushMessage data) throws FirebaseMessagingException {
        Optional<CoreUser> user = coreUserService.findById(no);
        if (user.isPresent()) {
            CoreUser it = user.get();
            Notification notification = Notification.builder().setTitle(data.getTitle()).setBody(data.getBody()).setImage(data.getImage()).build();
            Message.Builder builder = Message.builder();
            Optional.ofNullable(data.getData()).ifPresent(sit -> builder.putAllData(sit));
            Message msg = builder.setToken(it.getPushToken()).setNotification(notification).build();
            fcmService.sendMessage(msg);
        }
    }



}