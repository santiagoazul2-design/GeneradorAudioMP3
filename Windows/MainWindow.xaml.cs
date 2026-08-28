using System.Speech.Synthesis;
using System.Windows;
using System.Windows.Controls;
using NAudio.Wave;
using System.IO;

namespace GeneradorAudioMP3
{
    public partial class MainWindow : Window
    {
        private SpeechSynthesizer synthesizer;
        private string currentAudioPath;

        public MainWindow()
        {
            InitializeComponent();
            InitializeSynthesizer();
            SetupEventHandlers();
        }

        private void InitializeSynthesizer()
        {
            synthesizer = new SpeechSynthesizer();
            synthesizer.Volume = 100;
            
            var audioPath = Path.Combine(Path.GetTempPath(), "GeneradorAudioMP3");
            Directory.CreateDirectory(audioPath);
            currentAudioPath = Path.Combine(audioPath, "audio_generado.wav");

            StatusText.Text = "Listo para generar audio.";
        }

        private void SetupEventHandlers()
        {
            VolumeSlider.ValueChanged += (s, e) =>
            {
                synthesizer.Volume = (int)VolumeSlider.Value;
                VolumeLabel.Text = $"{(int)VolumeSlider.Value}%";
            };

            PitchSlider.ValueChanged += (s, e) =>
            {
                var pitch = (int)PitchSlider.Value;
                if (pitch < 0)
                    PitchLabel.Text = "Grave";
                else if (pitch > 0)
                    PitchLabel.Text = "Agudo";
                else
                    PitchLabel.Text = "Normal";
            };
        }

        private void GenerateButton_Click(object sender, RoutedEventArgs e)
        {
            var text = TextInput.Text.Trim();
            if (string.IsNullOrEmpty(text))
            {
                MessageBox.Show("Por favor ingresa un texto.", "Validación", MessageBoxButton.OK, MessageBoxImage.Warning);
                return;
            }

            GenerateAudio(text);
        }

        private void PreviewButton_Click(object sender, RoutedEventArgs e)
        {
            var text = TextInput.Text.Trim();
            if (string.IsNullOrEmpty(text))
            {
                MessageBox.Show("Por favor ingresa un texto.", "Validación", MessageBoxButton.OK, MessageBoxImage.Warning);
                return;
            }

            PreviewAudio(text);
        }

        private void GenerateAudio(string text)
        {
            try
            {
                StatusText.Text = "Generando audio...";
                StatusText.Foreground = System.Windows.Media.Brushes.Orange;
                GenerateButton.IsEnabled = false;

                var voice = VoiceCombo.SelectedIndex == 0 ? "male" : "female";
                var language = LanguageCombo.SelectedItem as ComboBoxItem;
                var speed = GetSpeed();

                synthesizer.Rate = speed;
                
                synthesizer.SelectVoiceByHints(
                    voice == "male" ? VoiceGender.Male : VoiceGender.Female);

                using (var fileStream = new FileStream(currentAudioPath, FileMode.Create))
                {
                    synthesizer.SetOutputToWaveFile(fileStream);
                    synthesizer.Speak(text);
                }

                ConvertWavToMp3(currentAudioPath);

                StatusText.Text = "✓ MP3 generado correctamente.";
                StatusText.Foreground = System.Windows.Media.Brushes.Green;
                
                MessageBox.Show($"Archivo guardado en:\n{currentAudioPath.Replace(".wav", ".mp3")}", 
                    "Éxito", MessageBoxButton.OK, MessageBoxImage.Information);
            }
            catch (Exception ex)
            {
                StatusText.Text = $"✗ Error: {ex.Message}";
                StatusText.Foreground = System.Windows.Media.Brushes.Red;
                MessageBox.Show($"Error al generar audio:\n{ex.Message}", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
            finally
            {
                GenerateButton.IsEnabled = true;
            }
        }

        private void PreviewAudio(string text)
        {
            try
            {
                StatusText.Text = "Reproduciendo preescucha...";
                StatusText.Foreground = System.Windows.Media.Brushes.Blue;
                
                var speed = GetSpeed();
                synthesizer.Rate = speed;
                synthesizer.SelectVoiceByHints(
                    VoiceCombo.SelectedIndex == 0 ? VoiceGender.Male : VoiceGender.Female);
                
                synthesizer.Speak(text);
                
                StatusText.Text = "Listo para generar audio.";
                StatusText.Foreground = System.Windows.Media.Brushes.Green;
            }
            catch (Exception ex)
            {
                StatusText.Text = $"✗ Error: {ex.Message}";
                StatusText.Foreground = System.Windows.Media.Brushes.Red;
            }
        }

        private int GetSpeed()
        {
            return SpeedCombo.SelectedIndex switch
            {
                0 => -3,      // Lenta
                1 => 0,       // Normal
                2 => 3,       // Rápida
                _ => 0
            };
        }

        private void ConvertWavToMp3(string wavPath)
        {
            var mp3Path = wavPath.Replace(".wav", ".mp3");
            
            using (var reader = new WaveFileReader(wavPath))
            using (var writer = new LameMP3FileWriter(mp3Path, reader.WaveFormat, LAMEPreset.V2))
            {
                reader.CopyTo(writer);
            }

            if (File.Exists(wavPath))
                File.Delete(wavPath);
        }

        private void Window_Closing(object sender, System.ComponentModel.CancelEventArgs e)
        {
            synthesizer?.Dispose();
        }
    }
}
