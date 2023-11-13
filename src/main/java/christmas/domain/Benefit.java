package christmas.domain;

import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;

public class Benefit {

    private final Map<Event, Integer> details;

    public Benefit() {
        this.details = new EnumMap<>(Event.class);
        initialBenefitDetails();
    }

    public int calculateTotalDiscount() {
        return details.values()
                .stream()
                .reduce(0, Integer::sum);
    }

    public void addDiscountIntoDetails(Event event, int discount) {
        details.computeIfPresent(event, (key, value) -> value + discount);
    }

    public String detailsToString() {
        StringBuilder sb = new StringBuilder("없음\n");
        if (calculateTotalDiscount() != 0) {
            sb.setLength(0);
            appendDetails(sb);
        }
        return sb.toString();
    }

    public int calculatePresentationPrice() {
        return details.get(Event.PRESENTATION);
    }

    public String presentationMenuToString() {
        Event presentation = Event.PRESENTATION;
        StringBuilder sb = new StringBuilder("없음\n");
        int discount = details.get(presentation);
        if (discount != 0) {
            sb.setLength(0);
            sb.append(presentation.getTarget());
            sb.append(" ");
            sb.append(String.format("%d개", Math.abs(discount / Event.PRESENTATION.getDiscount())));
            sb.append("\n");
        }
        return sb.toString();
    }


    private void appendDetails(StringBuilder benefitDetail) {
        for (Entry<Event, Integer> entry : details.entrySet()) {
            String eventMessage = entry.getKey().getMessage();
            int discount = entry.getValue();
            if (discount != 0) {
                benefitDetail.append(eventMessage);
                benefitDetail.append(" ");
                benefitDetail.append(String.format("%,d원", discount));
                benefitDetail.append("\n");
            }
        }
    }

    private void initialBenefitDetails() {
        Event.getAllEvent().forEach(event -> details.put(event, 0));
    }

}
