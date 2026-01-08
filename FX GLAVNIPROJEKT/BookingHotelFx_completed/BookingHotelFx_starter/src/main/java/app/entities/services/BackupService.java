package app.entities.services;

import app.entities.backup.BackupData;

import java.io.*;
import java.util.logging.Logger;

public class BackupService {

    private static final String BACKUP_FILE = "backup.bin";
    Logger log = Logger.getLogger(BackupService.class.getName());

    public void createBackup(HotelService hs,
                             GuestService gs,
                             ReservationService rs) {
        BackupData data = new BackupData(
                hs.getAll(),
                gs.getAll(),
                rs.getAll()
        );
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(BACKUP_FILE))) {
            oos.writeObject(data);
           log.info("Backup je kreiran u " + BACKUP_FILE);
        } catch (IOException e) {
            log.info("Greška pri kreiranju backup-a: " + e.getMessage());
        }
    }

    public void restoreBackup(HotelService hs,
                              GuestService gs,
                              ReservationService rs) {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(BACKUP_FILE))) {

            BackupData data = (BackupData) ois.readObject();

            hs.replaceAll(data.getHotels());
            gs.replaceAll(data.getGuests());
            rs.replaceAll(data.getReservations());

        } catch (FileNotFoundException e) {
          log.info("backup.bin ne postoji!");
        } catch (IOException | ClassNotFoundException e) {
            log.info("Greška pri učitavanju backup-a: " + e.getMessage());
        }
    }
}
