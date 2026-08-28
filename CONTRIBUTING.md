# 🤝 Contribuciones

¡Gracias por tu interés en contribuir! Este proyecto es open-source y aceptamos contribuciones.

## 🎯 Cómo empezar

1. **Fork** el repositorio
2. **Clone** tu fork
3. **Crea una rama** para tu feature
4. **Haz cambios** y commits
5. **Push** a tu fork
6. **Abre Pull Request**

## 📋 Proceso de Contribución

### 1. Reportar Issues
- Usa templates de GitHub Issues
- Describe el problema claramente
- Incluye pasos para reproducir
- Adjunta screenshots/logs si es posible

### 2. Sugerir Features
- Abre un Discussion primero
- Discute la idea con maintainers
- Si se aprueba, crea un Issue
- Sigue el proceso de Pull Request

### 3. Code Contributions
```bash
# Clonar tu fork
git clone https://github.com/TU_USUARIO/GeneradorAudioMP3.git
cd GeneradorAudioMP3

# Crear rama para feature
git checkout -b feature/mi-feature

# Hacer cambios...
git add .
git commit -m "feat: Descripción de cambios"
git push origin feature/mi-feature

# Abrir Pull Request
```

## 📏 Estándares de Código

### Android (Kotlin)
- Seguir [Kotlin Style Guide](https://kotlinlang.org/docs/coding-conventions.html)
- Usar MVVM architecture
- Tests para lógica de negocio
- Comentarios para lógica compleja

### Windows (C#)
- Seguir [C# Coding Conventions](https://docs.microsoft.com/en-us/dotnet/csharp/fundamentals/coding-style/coding-conventions)
- Usar MVVM o similar
- Unit Tests
- XML documentation comments

### Commits
```
Formato: <tipo>(<scope>): <descripción>

feat(android): agregar nueva voz
fix(windows): corregir crash en exportar
docs: actualizar README
```

Tipos: `feat`, `fix`, `docs`, `style`, `refactor`, `test`, `chore`

## 🧪 Testing

Antes de hacer Push, verifica:
```bash
# Android
./gradlew test lint

# Windows
dotnet test

# General
git log -1  # Verifica commit message
```

## ✅ Checklist para Pull Request

- [ ] Código sigue estándares del proyecto
- [ ] Tests pasan localmente
- [ ] Documentación actualizada
- [ ] No hay conflictos con main
- [ ] Cambios son focalizados y claros

## 🚫 No aceptamos:
- ❌ PRs con cambios de estilo solamente
- ❌ Cambios sin tests
- ❌ Código obfuscado intencionalmente
- ❌ Cambios sin descripción

## 💡 Ideas para contribuir

- 🐛 Arreglar bugs conocidos
- ✨ Nuevas voces/idiomas
- 📱 Mejoras UI/UX
- 🧪 Tests
- 📖 Documentación
- 🌍 Traducciones
- ⚡ Optimizaciones

## 📞 Contacto

- **Maintainer**: Santiago Azul
- **Email**: [contacto@ejemplo.com]
- **Discord**: [Enlace al servidor]

---

**¡Gracias por contribuir!** 🙏
