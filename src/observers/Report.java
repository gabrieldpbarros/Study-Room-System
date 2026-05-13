package observers;
import events.NotificationEvent;
import interfaces.INotificationEvent;
import interfaces.IObserver;

public class Report implements IObserver {
    private int id;

    public Report(int id) {
        this.setId(id);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    @Override
    public void update(INotificationEvent event) {
        if (event instanceof NotificationEvent roomEvent) {

            String roomInfo = roomEvent.getRoomType() + " (ID: " + roomEvent.getTargetId() + ")";
            String dateTime = roomEvent.getDate() + " às " + roomEvent.getTime() + "h";

            // Extração segura dos nomes (evita NullPointerException)
            String previousName = (roomEvent.getPreviousOccupant() != null) ? roomEvent.getPreviousOccupant().getName() : "Nenhum";
            String newName = (roomEvent.getNewOccupant() != null) ? roomEvent.getNewOccupant().getName() : "Nenhum";

            // Formatação do Log no Console
            System.out.println("====== [RELATÓRIO DE AUDITORIA #" + this.id + "] ======");
            System.out.printf("SALA:       %s\n", roomInfo);
            System.out.printf("DATA/HORA:  %s\n", dateTime);
            System.out.printf("PROCEDIMENTO: %s\n", roomEvent.getEventType());

            System.out.print("DETALHES:   ");
            switch (roomEvent.getEventType()) {
                case "RESERVA":
                    System.out.printf("O usuário '%s' ocupou o horário.\n", newName);
                    break;

                case "CANCELAMENTO":
                    System.out.printf("O usuário '%s' desocupou o horário.\n", previousName);
                    break;

                case "SOBRESCRITA":
                    System.out.printf("Conflito resolvido por Política. '%s' substituiu '%s'.\n",
                            newName, previousName);
                    break;

                default:
                    System.out.println("Registro não mapeado pelo sistema.");
            }
            System.out.println("=========================================\n");
        }
    }
}
