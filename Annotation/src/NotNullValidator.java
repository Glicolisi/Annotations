public class NotNullValidator implements Validator{
    @Override
    public boolean validate(Object o) {
        return o != null;
    }
}
