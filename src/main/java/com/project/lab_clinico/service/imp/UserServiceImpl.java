    package com.project.lab_clinico.service.imp;

    import com.project.lab_clinico.entity.LaboratoristaEntity;
    import com.project.lab_clinico.entity.MedicoEntity;
    import com.project.lab_clinico.entity.TypeUser;
    import com.project.lab_clinico.entity.UserEntity;
    import com.project.lab_clinico.repository.LaboratoristaRepository;
    import com.project.lab_clinico.repository.MedicoRepository;
    import com.project.lab_clinico.repository.PacientRepository;
    import com.project.lab_clinico.repository.UserRepository;
    import com.project.lab_clinico.service.UserService;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Optional;

    @Service
    public class UserServiceImpl implements UserService {
        private final UserRepository userRepository;
        private final MedicoRepository medicoRepository;
        private final LaboratoristaRepository laboratoristaRepository;
        private final PacientRepository pacientRepository;

        public UserServiceImpl(UserRepository userRepository,
                               MedicoRepository medicoRepository,
                               LaboratoristaRepository laboratoristaRepository, PacientRepository pacientRepository) {
            this.userRepository = userRepository;
            this.medicoRepository = medicoRepository;
            this.laboratoristaRepository = laboratoristaRepository;
            this.pacientRepository = pacientRepository;
        }

        @Override
        public List<UserEntity> listar() {
            return userRepository.findAll();
        }

        @Override
        public Optional<UserEntity> buscarPorId(Long id) {
            return userRepository.findById(id);
        }

        @Override
        public UserEntity guardar(UserEntity user) {
            // Guardar usuario
            UserEntity savedUser = userRepository.save(user);

            // Si es MÉDICO y viene la entidad médico, guardar en tabla medico
            if (savedUser.getTipoUsuario() == TypeUser.MEDICO && user.getMedico() != null) {
                MedicoEntity medico = user.getMedico();
                medico.setUserEntity(savedUser);  // Asignar relación
                medicoRepository.save(medico);
            }

            // Si es LABORATORISTA y viene la entidad laboratorista, guardar en tabla laboratorista
            if (savedUser.getTipoUsuario() == TypeUser.LABORATORISTA && user.getLaboratorista() != null) {
                LaboratoristaEntity laboratorista = user.getLaboratorista();
                laboratorista.setUserEntity(savedUser);  // Asignar relación
                laboratoristaRepository.save(laboratorista);
            }

            // Si es PACIENTE y viene la entidad paciente, guardar en tabla paciente (opcional si lo implementas)
            if (savedUser.getTipoUsuario() == TypeUser.PACIENTE && user.getPaciente() != null) {
                var paciente = user.getPaciente();
                paciente.setUserEntity(savedUser);
                pacientRepository.save(paciente);
            }

            return savedUser;
        }

        @Override
        public UserEntity actualizar(Long id, UserEntity user) {
            return userRepository.findById(id)
                    .map(existingUser -> {
                        existingUser.setNombres(user.getNombres());
                        existingUser.setApellidos(user.getApellidos());
                        existingUser.setTelefono(user.getTelefono());
                        existingUser.setDni(user.getDni());
                        existingUser.setSexo(user.getSexo());
                        existingUser.setFechaNacimiento(user.getFechaNacimiento());
                        existingUser.setTipoUsuario(user.getTipoUsuario());
                        return userRepository.save(existingUser);
                    }).orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + id));
        }

        @Override
        public void eliminar(Long id) {
            Optional<UserEntity> userOpt = userRepository.findById(id);
            if (userOpt.isPresent()) {
                UserEntity user = userOpt.get();

                // Eliminar entidades relacionadas
                if (user.getTipoUsuario() == TypeUser.MEDICO && user.getMedico() != null) {
                    medicoRepository.delete(user.getMedico());
                }
                if (user.getTipoUsuario() == TypeUser.LABORATORISTA && user.getLaboratorista() != null) {
                    laboratoristaRepository.delete(user.getLaboratorista());
                }
                if (user.getTipoUsuario() == TypeUser.PACIENTE && user.getPaciente() != null) {
                    pacientRepository.delete(user.getPaciente());
                }

                // Eliminar usuario
                userRepository.delete(user);
            } else {
                throw new RuntimeException("Usuario no encontrado");
            }
        }

        @Override
        public List<UserEntity> listarPorTipo(TypeUser tipoUsuario) {
            return userRepository.findAllByTipoUsuario(tipoUsuario);
        }

        @Override
        public Optional<UserEntity> buscarPorDniYTipo(String dni, TypeUser tipoUsuario) {
            return userRepository.findByDniAndTipoUsuario(dni, tipoUsuario);
        }
    }
